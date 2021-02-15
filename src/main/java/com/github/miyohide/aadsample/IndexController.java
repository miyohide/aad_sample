package com.github.miyohide.aadsample;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.github.miyohide.aadsample.JsonMapper.toJsonString;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model, OAuth2AuthenticationToken authentication, @RegisteredOAuth2AuthorizedClient("azure")OAuth2AuthorizedClient azureClient) {
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("clientName", azureClient.getClientRegistration().getClientName());
        return "index";
    }

    @GetMapping("/graph")
    @ResponseBody
    public String graph(
            @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphClient
    ) {
        return toJsonString(graphClient);
    }
}
