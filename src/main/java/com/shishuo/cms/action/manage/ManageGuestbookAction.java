package com.shishuo.cms.action.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.constant.GuestbookConstant;
import com.shishuo.cms.entity.vo.GuestbookVo;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.service.GuestbookService;
import com.shishuo.cms.util.SSUtils;

@RequestMapping("/manage/guestbook")
@Controller
public class ManageGuestbookAction extends ManageBaseAction {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String login(ModelMap modelMap,
			@RequestParam(value = "p", defaultValue = "1") int p)
			throws Exception {
		PageVo<GuestbookVo> pageVo = guestbookService.getMessageBoardPage(p,
				null, null);
		modelMap.put("pageVo", pageVo);
		return "manage/guestbook/list";
	}

	@RequestMapping(value = "/details.htm", method = RequestMethod.GET)
	public String details(@RequestParam("guestbookId") long guestbookId,
			ModelMap modelMap, HttpServletRequest request) throws Exception {
		GuestbookVo guestbook = guestbookService.getGuestbookById(guestbookId);
		modelMap.put("guestbook", guestbook);
		return "manage/guestbook/update";
	}

	@RequestMapping(value = "/examine.htm", method = RequestMethod.GET)
	public String examine(@RequestParam("guestbookId") long guestbookId,
			@RequestParam("status") GuestbookConstant.status status,
			ModelMap modelMap, HttpServletRequest request) throws Exception {
		guestbookService.updateStatusByMessageId(status, guestbookId);
		GuestbookVo guestbook = guestbookService.getGuestbookById(guestbookId);
		modelMap.put("guestbook", guestbook);
		return "manage/guestbook/update";
	}

	@ResponseBody
	@RequestMapping(value = "/addReply.json", method = RequestMethod.POST)
	public JsonVo<String> addReply(@RequestParam(value = "reply") String reply,
			@RequestParam(value = "guestbookId") long guestbookId,
			@RequestParam(value = "status") GuestbookConstant.status status,
			ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		if (StringUtils.isBlank(reply)) {
			json.getErrors().put("reply", "回复内容不能为空");
			json.setMsg("回复内容不能为空");
		}
		try {
			// 检测校验结果
			json.check();
			guestbookService.updateReplyByMessageId(SSUtils.toText(reply),
					guestbookId, status);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/status.json", method = RequestMethod.POST)
	public JsonVo<String> status(
			@RequestParam(value = "guestbookId") long guestbookId,
			@RequestParam(value = "status") GuestbookConstant.status status,
			ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			// 检测校验结果
			json.check();
			guestbookService.updateStatusByMessageId(status, guestbookId);
			json.setResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

}
