package de.fhflensburg.pd.group002.input;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.IInputValidator;

public class Validator implements IInputValidator {
	ArrayList<String> list = new ArrayList<String>();
	public Validator(){
		list.add("help");                                
		list.add("list");                               
		list.add("assetic:dump");                        
		list.add("assets:install");                      
		list.add("cache:clear");                        
		list.add("cache:warmup");                        
		list.add("config:dump-reference");              
		list.add("container:debug");                   
		list.add("doctrine:cache:clear-metadata");       
		list.add("doctrine:cache:clear-query");          
		list.add("doctrine:cache:clear-result");         
		list.add("doctrine:database:create");            
		list.add("doctrine:database:drop");                
		list.add("doctrine:ensure-production-settings");  
		list.add("doctrine:generate:crud");               
		list.add("doctrine:generate:entities");          
		list.add("doctrine:generate:entity");            
		list.add("doctrine:generate:form");              
		list.add("doctrine:mapping:convert");           
		list.add("doctrine:mapping:import");            
		list.add("doctrine:mapping:info");              
		list.add("doctrine:query:dql");                 
		list.add("doctrine:query:sql");                 
		list.add("doctrine:schema:create");             
		list.add("doctrine:schema:drop");              
		list.add("doctrine:schema:update");            
		list.add("doctrine:schema:validate");          
		list.add("generate:bundle");                   
		list.add("generate:controller");                
		list.add("generate:doctrine:crud");             
		list.add("generate:doctrine:entities");          
		list.add("generate:doctrine:entity");        
		list.add("generate:doctrine:form");           
		list.add("init:acl");   
		list.add("router:debug");        
		list.add("router:dump-apache");               
		list.add("router:match");                         
		list.add("server:run");                          
		list.add("swiftmailer:debug");                     
		list.add("swiftmailer:spool:send");  
		list.add("translation:update");
		list.add("twig:lint");
	}
	@Override
	public String isValid(String newText) {
		String suggestions = "";
		String[] command = newText.split(" ");		
		
		if(!newText.isEmpty()) {
			for(String item:list){
				if(item.startsWith(newText))
					suggestions = suggestions + " | " + item;
			}			
		}
			
		for(String item:list){
			if(item.equals(command[0]))
				return null;
		}
		return suggestions;
	}

}
