package com.xworkz.medisales.constants;

import lombok.Getter;

@Getter
public enum ServiceConstant {

    //for mail and update pages

    FILE_PATH("D://medisalesproject/profile/"),FROM_MAIL("madullasowmya29@gmail.com"),PASSWORD("edxn tpkj qfou xoak"),FROM_NAME("Medi Sales Project");
    private String path;

    ServiceConstant(String path){
        this.path =path;
    }
}
