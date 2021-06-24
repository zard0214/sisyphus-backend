package com.sisyphus.auth.authorize.uitls;

import com.google.common.collect.Lists;
import com.sisyphus.auth.authorize.model.vo.AuthMenuVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author zhecheng.zhao
 * @date Created in 09/06/2021 10:13
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeUtil {

	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list     具有树形结构特点的集合
	 * @param parentId 父节点ID
	 *
	 * @return 树形结构集合 child menu vos
	 */
	public static List<AuthMenuVO> getChildMenuVos(List<AuthMenuVO> list, Long parentId) {
		List<AuthMenuVO> returnList = Lists.newArrayList();
		for (AuthMenuVO menuVo : list) {
			if (menuVo.getPid() == null) {
				continue;
			}
			if (Objects.equals(menuVo.getPid(), parentId)) {
				recursionFn(list, menuVo);
				returnList.add(menuVo);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 */
	private static void recursionFn(List<AuthMenuVO> list, AuthMenuVO t) {
		// 得到子节点列表
		List<AuthMenuVO> childList = getChildList(list, t);
		t.setSubMenu(childList);
		if (!CollectionUtils.isEmpty(childList)) {
			t.setHasMenu(true);
		}
		for (AuthMenuVO tChild : childList) {
			// 判断是否有子节点
			if (hasChild(list, tChild)) {
				for (AuthMenuVO n : childList) {
					recursionFn(list, n);
				}
				tChild.setHasMenu(true);
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private static List<AuthMenuVO> getChildList(List<AuthMenuVO> list, AuthMenuVO t) {
		List<AuthMenuVO> tList = Lists.newArrayList();

		for (AuthMenuVO menuVo : list) {
			//TODO
			if (menuVo.getPid() == null) {
				continue;
			}
			if (Objects.equals(menuVo.getPid(), t.getId())) {
				tList.add(menuVo);
			}
		}
		return tList;
	}

	/**
	 * 判断是否有子节点
	 */
	private static boolean hasChild(List<AuthMenuVO> list, AuthMenuVO t) {
		return !getChildList(list, t).isEmpty();
	}

}
