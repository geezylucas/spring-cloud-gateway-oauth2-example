package com.geezylucas.oauth2gateway.api;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Operación exitosa"),
    FAILED(500, "Operación fallida"),
    VALIDATE_FAILED(404, "Prueba de parámetros fallida"),
    UNAUTHORIZED(401, "Aún no ha iniciado sesión o el token ha caducado"),
    FORBIDDEN(403, "Sin permisos relevantes");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
