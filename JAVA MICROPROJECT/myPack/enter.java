//===== Press "ENTER" to continue=====//
//Silent and Non-Silent//
package myPack;
import java.io.*;
public class enter
{
	public void promptEnterKey()
	{
		System.out.println("Press \"ENTER\" to continue");
		try{
			System.in.read();	//Waits for the user's Blank Input
		}
		catch(IOException e){
			//Exception Handling in-case user does not give Blank Input
		}
	}
	public void silentEnterKey()
	{
		try{
			System.in.read();	//Waits for the user's Blank Input
		}
		catch(IOException e){
			//Exception Handling in-case user does not give Blank Input
		}
	}
}