package com.jnjcomu.edison.api;

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-04-12
 */

public interface EdisonAPI {
    // TODO : Call 작성 (현재는 간단한 설계만)
    void login();
    void sendDeviceLocation();
    void fetchNewAuthKey();
}