package osc.service;

import osc.model.LoginRequest;
import osc.model.LoginResponse;
import osc.model.RefreshTokenRequest;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}