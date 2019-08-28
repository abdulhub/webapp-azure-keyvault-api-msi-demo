package com.abdul.azure.javaprojects.webappazurekeyvaultapimsidemo;

 

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.AppServiceMSICredentials;
import com.microsoft.azure.keyvault.KeyVaultClient;

@RestController
public class AzureKeyVaultController {

	@GetMapping("/hellokeyvault")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "spring-datasource-username") String secretName,
			Model model) {

		 
	 
		AppServiceMSICredentials credentials = new AppServiceMSICredentials(AzureEnvironment.AZURE);

		KeyVaultClient keyVaultClient = new KeyVaultClient(credentials);
		String secretValue  = keyVaultClient.getSecret("https://abdulkeyvault.vault.azure.net/", secretName).value();

		// SecretBundle userName =
		// keyVaultClient.getSecret("https://abdulkeyvault.vault.azure.net/secrets/spring-datasource-username/36704164ae714647971c1d837dd2ae4d","spring-datasource-username");

		return secretValue;
	}

	@GetMapping("/hello")
	public String greetings(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return name;
	}

}
