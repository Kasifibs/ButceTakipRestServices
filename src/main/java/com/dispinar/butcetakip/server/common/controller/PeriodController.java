package com.dispinar.butcetakip.server.common.controller;

import java.util.List;

import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodExpensesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodIncomesInformationDTO;
import com.dispinar.butcetakip.server.common.controller.dto.period.PeriodResourcesInformationDTO;
import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.query.PeriodQueryParamsWrapper;
import com.dispinar.butcetakip.server.common.service.PeriodService;



@RestController
@RequestMapping("/period")
public class PeriodController {
	
	@Autowired PeriodService periodService;
	
	@RequestMapping(value="/kaydet", method=RequestMethod.POST)
	public void savePeriod(@RequestBody Period period){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		
	    periodService.savePeriod(period, username);
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET)
	public List<Period> getAllPeriods(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return periodService.getAllPeriods(username);
	}
	
	@RequestMapping(value="/sorgula", method=RequestMethod.GET)
	public QueryResultWrapper<Period> queryPeriods(PeriodQueryParamsWrapper queryParams){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	      
		return periodService.performQueryAndPrepareResultWrapper(username, queryParams);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Period getPeriod(@PathVariable("id") Long id){
		return periodService.getPeriod(id);
	}

	@RequestMapping(value="/guncelle", method=RequestMethod.PUT)
	public Period updatePeriod(@RequestBody Period period){
		return periodService.updatePeriod(period);
	}
	
	@RequestMapping(value="/sil/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePeriod(@PathVariable("id") Long id){
		periodService.deletePeriod(id);

		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}

    @RequestMapping(value="/donemVarlikBilgisiniGetir", method=RequestMethod.GET)
    public PeriodResourcesInformationDTO getPeriodResourcesInformation(Long periodId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return periodService.preparePeriodResourcesInformation(username, periodId);
    }

    @RequestMapping(value="/donemGelirBilgisiniGetir", method=RequestMethod.GET)
    public PeriodIncomesInformationDTO getPeriodIncomesInformation(Long periodId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return periodService.preparePeriodIncomesInformation(username, periodId);
    }

    @RequestMapping(value="/donemGiderBilgisiniGetir", method=RequestMethod.GET)
    public PeriodExpensesInformationDTO getPeriodExpensesInformation(Long periodId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return periodService.preparePeriodExpensesInformation(username, periodId);
    }
}
