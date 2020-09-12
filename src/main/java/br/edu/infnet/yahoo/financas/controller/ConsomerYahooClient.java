package br.edu.infnet.yahoo.financas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.edu.infnet.yahoo.financas.controller.dto.FinancesWrapper;

@Controller
public class ConsomerYahooClient {
	
	private static final String SERV_URL = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=TLS.AX,MUS.AX";
	private RestTemplate restClient = new RestTemplate();
	
	@GetMapping("/financeiro")
	public ModelAndView financialData(Model model) {
		
		ModelAndView mv = new ModelAndView("financeiro");
		try {
			
			FinancesWrapper wrapper = restClient.getForObject(SERV_URL, FinancesWrapper.class);
			
			wrapper.getFinances().getQuoteResponse().forEach(f2 ->  System.err.println(f2.toString()));
			
			model.addAttribute("financesDTO", wrapper.getFinances().getQuoteResponse());
			mv.addObject(model);
		} catch (ResourceAccessException e) {
			
			System.err.println("Erro com o HOST");
		}
		return mv;		
	}
}
