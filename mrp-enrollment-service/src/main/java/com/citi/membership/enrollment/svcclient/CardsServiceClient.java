package com.citi.membership.enrollment.svcclient;

import java.util.List;

import com.citi.membership.enrollment.model.CardDetailsRequest;
import com.citi.membership.enrollment.model.CardDetailsResp;

public interface CardsServiceClient {
	
	CardDetailsResp getCardDetails(String cardnum);
	



}
