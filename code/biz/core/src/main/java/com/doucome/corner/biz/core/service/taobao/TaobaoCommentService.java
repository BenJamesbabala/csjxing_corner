package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoCommentEnum;

/**
 * ��Ʒ����service.
 * @author ze2200
 *
 */
public interface TaobaoCommentService {
	/**
	 * ��ȡ��Ʒ����.
	 * @param itemNativeId ��Ʒ���Ա�����ID
	 * @param sellerId ��Ʒ������id.
	 * @param commentEnum 
	 * @return ��Ʒ�����б�null������ʧ��.
	 */
	List<TaobaoCommentDTO> getItemComments(Long itemNativeId, Long sellerId, TaobaoCommentEnum commentEnum);
}
