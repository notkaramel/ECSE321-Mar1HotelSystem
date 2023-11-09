package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

public class MultiplePaymentDto {
    private List<PaymentResponseDto> paymentList;

    public MultiplePaymentDto() {
        this.paymentList = new ArrayList<>();
    }

    public MultiplePaymentDto(Iterable<Payment> payments) {
        this.paymentList = new ArrayList<>();
        for (Payment payment : payments) {
            this.paymentList.add(new PaymentResponseDto(payment));
        }
    }

    public List<PaymentResponseDto> getPaymentList() {
        return this.paymentList;
    }

    public void setPaymentList(List<PaymentResponseDto> paymentList) {
        this.paymentList = paymentList;
    }
}
