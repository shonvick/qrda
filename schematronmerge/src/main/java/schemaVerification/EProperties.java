package schemaVerification;

import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;


public class EProperties extends Properties{


	/*
	public static void main(String [] args) {
		String fileName = "/Users/shonvick/Documents/projects/mavenizedSchematron/qrda/schematronmerge/src/main/java/schemaVerification/test.properties";

		try {

			File file = new File(fileName);
			FileInputStream fileInput = new FileInputStream(file);

			EProperties properties = new EProperties();
			properties.load(fileInput);
			properties.substituteVars();
			System.out.println(properties);

			fileInput.close();


		} catch (Exception e) {
			System.err.println("Could not load property file " + fileName);
			e.printStackTrace();
		} 	

		
	}
*/
	
	public  EProperties () {
		substituteVars();
	}
	
	private void substituteVars(){
		
		for(String prop : stringPropertyNames()) {
			
			String oldValue = getProperty(prop);
			
			System.out.println("Old Value for " + prop + " = " + oldValue);
			
			// Find all the vars to subsitute
			Set<String> subs = findVars(oldValue);
			
			String propValue = getProperty(prop);
			String newValue = propValue;
			
			
			// for each var to substitute
			for (String var : subs) {
				// replace the variable with the value
				
				newValue = getProperty(var);
				String toReplace = "\\$"+var;

				propValue  = propValue.replaceAll(toReplace, newValue);		

			}
			propValue = propValue.replace("{", "").replace("}", "");
			setProperty(prop,propValue);
			
		}
	}



	private Set<String >findVars(String stringIn){
		// A Variable is defined as any string starting with the char '$' and ending with 
		// whitespace or another '$'
		// inside of '{' and '}'
		// examples: 
		//   {$a$b}  contains the variable $a and $b 
		//   {$a $b} contains the variable $a and $b 


		// step 1 - pick off the '{' '}'
		String regexBrackets = "\\{(\\s*\\$\\w+\\s*)*\\}";
		Pattern brackets = Pattern.compile(regexBrackets);
		Set<String> vars = new HashSet<String>();

		Matcher matchBrackets =  brackets.matcher(stringIn);

		while (matchBrackets.find()){
			String varGroup = matchBrackets.group();
			//System.out.println("found group " + varGroup);

			// step 2 - get rid of brackets
			varGroup = varGroup.replace("{","").replace("}", "");
			//System.out.println("After[" + varGroup + "]");

			// step 3 - split based on '$'
			String varRefs[] = varGroup.split("\\$");
			for (String v : varRefs){
				v = v.trim();
				if (v.length() > 0) vars.add(v);
			}

		}

		return (vars);
	}
}




