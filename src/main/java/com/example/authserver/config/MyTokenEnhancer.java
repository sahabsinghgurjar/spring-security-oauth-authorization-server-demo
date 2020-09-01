package com.example.authserver.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.example.authserver.models.CustomUser;

public class MyTokenEnhancer extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Object obj = authentication.getPrincipal();
		DefaultOAuth2AccessToken customAccessToken = null;
		if (CustomUser.class.isAssignableFrom(obj.getClass())) {
			CustomUser customUser = (CustomUser) obj;
			Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

			customAccessToken = new DefaultOAuth2AccessToken(accessToken);
			customAccessToken.setAdditionalInformation(info);
		}
		return super.enhance(customAccessToken != null ? customAccessToken : accessToken, authentication);
	}

}
