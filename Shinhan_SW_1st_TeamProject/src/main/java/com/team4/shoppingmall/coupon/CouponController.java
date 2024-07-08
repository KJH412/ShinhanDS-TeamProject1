
package com.team4.shoppingmall.coupon;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.shoppingmall.member.MemberDTO;

@Controller
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    CouponService couponService;

    
    
    @PostMapping("/receive")
    @ResponseBody
    public Map<String, String> receiveCoupon(CouponDTO coupon, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            response.put("status", "error");
            response.put("message", "로그인 후 쿠폰을 받을 수 있습니다.");
            return response;
        }

        CouponDTO coupon2 = new CouponDTO();
        coupon2.setCoupon_id(coupon.getCoupon_id());
        coupon2.setMember_id(member.getMember_id());
        coupon2.setCoupon_name(coupon.getCoupon_name());
        coupon2.setDiscount_rate((double)coupon.getDiscount_rate());
        coupon2.setQuantity(1);

        int success = couponService.assignCouponToMember(coupon2);

        if (success == 1) {
            response.put("status", "success");
            response.put("message", "쿠폰이 성공적으로 발급되었습니다.");
        } else {
            response.put("status", "error");
            response.put("message", "쿠폰 발급에 실패했습니다.");
        }

        return response;
    }


    @PostMapping("/checkLogin")
    @ResponseBody
    public boolean checkLogin(HttpSession session) {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        return member != null;
    }
}
