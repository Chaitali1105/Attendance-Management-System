
package myPack;
public class dateValidation
{
	public boolean validateDate(String date)
	{
		String[] s = date.split("-");
        if(Integer.parseInt(s[2]) < 1000)
        {
            return false;
        }
        else if(Integer.parseInt(s[1]) < 0 || Integer.parseInt(s[1]) > 12)
            return false;
        else{
            if(
                Integer.parseInt(s[1]) == 01 || 
                Integer.parseInt(s[1]) == 03 || 
                Integer.parseInt(s[1]) == 05 || 
                Integer.parseInt(s[1]) == 07 || 
                Integer.parseInt(s[1]) == 8  || 
                Integer.parseInt(s[1]) == 10 || 
                Integer.parseInt(s[1]) == 12
            )
            {
                if(Integer.parseInt(s[0]) < 0 || Integer.parseInt(s[0]) > 31)
                {
                   return false;
                }      
            }
            else if(
                Integer.parseInt(s[1]) == 04 || 
                Integer.parseInt(s[1]) == 06 || 
                Integer.parseInt(s[1]) ==  9 || 
                Integer.parseInt(s[1]) == 11
            )
            {
                 if(Integer.parseInt(s[0]) < 0 || Integer.parseInt(s[0]) > 30)
                    return false;
            }
            else if(Integer.parseInt(s[1]) == 2)
            {
                if(Integer.parseInt(s[2]) % 4 == 0 || Integer.parseInt(s[2]) % 100 == 0)
                {
                    if(Integer.parseInt(s[0]) > 29 || Integer.parseInt(s[0]) < 0)
                        return false;
                }else
                {
                    if(Integer.parseInt(s[0]) > 28 || Integer.parseInt(s[0]) < 0)
                        return false;
                        
                }
            }
	    }
        return true;
    }
}