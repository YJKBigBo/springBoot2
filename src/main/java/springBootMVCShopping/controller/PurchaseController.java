package springBootMVCShopping.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import springBootMVCShopping.command.PurchaseCommand;
import springBootMVCShopping.service.purchase.*;

@Controller
@RequestMapping("purchase")
public class PurchaseController {
	@Autowired
	OrderProcessListService orderProcessListService;
	@Autowired
	GoodsBuyService goodsBuyService;
	@Autowired
	GoodsOrderService goodsOrderService;

	@Autowired
	IniPayReqService iniPayReqService;

	@Autowired
	INIstdpayPcReturn iNIstdpayPcReturn;

	@PostMapping("goodsBuy")
	public String goodsBuy(String nums[] , HttpSession session,Model model) {
		goodsBuyService.execute(nums, session, model);
		return "thymeleaf/purchase/goodsOrder";
	}

	@PostMapping("goodsOrder")
	public String goodsOrder(PurchaseCommand purchaseCommand, HttpSession session,
			Model model) {
		String purchaseNum = goodsOrderService.execute(purchaseCommand, session, model);
		return "redirect:paymentOk?purchaseNum=" + purchaseNum;
	}

	@GetMapping("paymentOk")
	public String paymentOk(String purchaseNum, Model model) {
        try {
            iniPayReqService.execute(purchaseNum, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "thymeleaf/purchase/payment";
	}

	@RequestMapping("INIstdpay_pc_return")
	public String payReturn(HttpServletRequest request) {
		iNIstdpayPcReturn.execute(request);
		return "thymeleaf/purchase/buyFinished";
	}
	@RequestMapping("close")
	public String close(){
		return "thymeleaf/purchase/close";
	}

	@GetMapping("orderList")
	public String orderList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model);
		return "thymeleaf/purchase/orderList";
	}

	@GetMapping("purchaseList")
	public String purchaseList(HttpSession session, Model model) {
		orderProcessListService.execute(session, model);
		return "thymeleaf/purchase/purchaseList";
	}
}
