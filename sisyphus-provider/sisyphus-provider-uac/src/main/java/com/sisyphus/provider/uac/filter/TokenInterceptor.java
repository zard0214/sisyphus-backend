package com.sisyphus.provider.uac.filter;

import com.sisyphus.common.base.constant.GlobalConstant;
import com.sisyphus.common.base.dto.AuthUserTokenDTO;
import com.sisyphus.common.base.dto.LoginAuthDTO;
import com.sisyphus.common.support.annotation.NoNeedAccessAuthentication;
import com.sisyphus.common.support.util.RedisKeyUtil;
import com.sisyphus.common.support.util.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author zhecheng.zhao
 * @date Created in 23/06/2021 01:14
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;

	private static final String MATE_TENANT_ID = "TenantId";
	private static final String OPTIONS = "OPTIONS";
	private static final String AUTH_PATH1 = "/auth";
	private static final String AUTH_LOGOUT_PATH1 = "/user/logout";
	private static final String AUTH_LOGINFO_PATH1 = "/user/loginInfo";
	private static final String AUTH_PATH2 = "/oauth";
	private static final String AUTH_PATH3 = "/error";
	private static final String AUTH_PATH4 = "/api";

	/**
	 * After completion.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param ex       the ex
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
		if (ex != null) {
			log.error("<== afterCompletion - ??????token??????. ex={}", ex.getMessage(), ex);
			this.handleException(response);
		}
	}

	/**
	 * Post handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param mv       the mv
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
	}

	/**
	 * Pre handle boolean.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param handler  the handler
	 *
	 * @return the boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String uri = request.getRequestURI();
		log.info("<== preHandle - ???????????????.  url={}", uri);
		if (uri.contains(AUTH_PATH1)
				&& !uri.contains(AUTH_LOGOUT_PATH1)
				&& !uri.contains(AUTH_LOGINFO_PATH1)  || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
			log.info("<== preHandle - ??????URL????????????.  url={}", uri);
			return true;
		}
		log.info("<== preHandle - ????????????????????????.  OPTIONS={}", request.getMethod().toUpperCase());
		if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
			log.info("<== preHandle - ????????????????????????.  url={}", uri);
			return true;
		}
		if (isHaveAccess(handler)) {
			log.info("<== preHandle - ?????????????????????????????????.  token={}");
			return true;
		}
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
		log.info("<== preHandle - ???????????????.  token={}", token);
		AuthUserTokenDTO authUserTokenDTO = (AuthUserTokenDTO) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token));
        LoginAuthDTO loginUser = new ModelMapper().map(authUserTokenDTO, LoginAuthDTO.class);
		if (loginUser == null) {
			log.error("????????????????????????, ???????????????");
			return false;
		}
		if(request.getHeader(MATE_TENANT_ID) != null){
			Long tenantId = Long.valueOf(request.getHeader(MATE_TENANT_ID));
			log.info("<== preHandle - ???????????????.  tenantId={}", tenantId);
			loginUser.setTenantId(tenantId);
			ThreadLocalMap.put(GlobalConstant.Sys.TENANT_ID, tenantId);
		}
		log.info("<== preHandle - ???????????????.  loginUser={}", loginUser);
		ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, loginUser);
		log.info("<== preHandle - ???????????????.  url={}, loginUser={}", uri, loginUser);
		return true;
	}

	private void handleException(HttpServletResponse res) throws IOException {
		res.resetBuffer();
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"code\":100009 ,\"message\" :\"??????token??????\"}");
		res.flushBuffer();
	}

	private boolean isHaveAccess(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
		return responseBody != null;
	}

}
  