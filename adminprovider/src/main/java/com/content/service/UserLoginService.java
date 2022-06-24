package com.content.service;

import com.content.POJO.vo.UserLoginRequestVO;

public interface UserLoginService {
    boolean userLoginStatus(UserLoginRequestVO requestVO);
}
