package com.qclaw.securitytraining.dto;
import lombok.Data;
@Data
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;
    public static <T> ApiResponse<T> success(T data) { ApiResponse<T> r = new ApiResponse<>(); r.setCode(200); r.setMessage("success"); r.setData(data); return r; }
    public static <T> ApiResponse<T> error(String msg) { ApiResponse<T> r = new ApiResponse<>(); r.setCode(500); r.setMessage(msg); return r; }
}