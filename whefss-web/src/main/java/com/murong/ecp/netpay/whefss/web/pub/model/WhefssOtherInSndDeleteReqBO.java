package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssOtherInSndDeleteReqBO {
	/**
	 * id
	 */
	@ECPDict(required = true, message = "ids值不合法")
	private String ids;

}
