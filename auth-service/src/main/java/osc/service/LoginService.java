package osc.service;

import osc.model.LoginRequest;
import osc.model.LoginResponse;
import osc.model.RefreshTokenRequest;

public interface LoginService {

    /**
     * Call login to get token
     * @param loginRequest
     * @return LoginResponse
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * Call refresh token
     * @param refreshTokenRequest
     * @return new token
     */
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}