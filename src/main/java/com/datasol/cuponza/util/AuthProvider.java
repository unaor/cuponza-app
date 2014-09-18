package com.datasol.cuponza.util;

public enum AuthProvider {
	CUPONZA("CuponZa"),
	TWITTER("Twitter"),
	FACEBOOK("FaceBook");
	
	private final String provider;
	
	private AuthProvider(String provider){
		this.provider=provider;
	}
}
