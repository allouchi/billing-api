package com.aliateck.fact;

import com.aliateck.fact.domaine.business.object.Adresse;
import com.aliateck.fact.domaine.business.object.Company;
import com.aliateck.fact.domaine.business.object.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class ApplicationStarter { //implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

    //@Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .userName("allouchi@hotmail.fr")
                .password("123456")
                .firstName("Mustapha")
                .lastName("Aliane")
                .activated(true)
                .id(1L)

                .build();

        Adresse adresse = Adresse.builder()
                .codePostal("92500")
                .localite("Rueil-Malmaison")
                .numero("111")
                .pays("France")
                .rue("Boulevard National")
                .build();

        Company company = Company.builder()
                .siret("85292702900011")
                .numeroBic("PSSTFRPPSCE")
                .codeApe("6201Z")
                .numeroIban("FR1720041010125407961J03367")
                .numeroTva("FR18831502141")
                .socialReason("SBATEC Consulting")
                .status("SASU au capital de 500 Euros")
                .rcsName("R.C.S. Nanterre 831 502 141")
                .companyAdresse(adresse)
                .id(1L)
                .build();
        user.setSiret("85292702900011");
        //userApiService.addUser(user);


    }
}
