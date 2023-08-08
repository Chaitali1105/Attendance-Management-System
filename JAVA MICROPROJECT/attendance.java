import java.io.*;
import java.util.*;
import myPack.dateValidation;

class data
{
	Scanner sc = new Scanner(System.in);
}
class read extends data
{
	public void m_read(String fileNm)
	{
		FileInputStream inputStream = null;
		int b;
		try
		{
			inputStream = new FileInputStream(fileNm+".txt");
			System.out.println("\n======================================");
			System.out.println("======|   ATTENDANCE DISPLAY   |======");
			System.out.println("======================================");
			while((b = inputStream.read())!=-1)	//Unless it reaches the END OF FILE...
			{	
				System.out.print((char)b);	//Keep Printing all characters present in the file
			}
			System.out.print("Done... ");
			inputStream.close();
		}
		catch(Exception e){
			System.out.println("\nSorry but, something went wrong... Exiting \"Attendance Display\"...");
		}
	}
}
class write extends data
{
	public void m_write(String fileNm)
	{
		try
		{
			PrintWriter outputStream = new PrintWriter(fileNm+".txt");  //Creates File and automatically adds the ".txt"
			System.out.print("Enter number of students in the class:- ");
			int total_stud = sc.nextInt();
			System.out.println("\n\nEnter all roll numbers that are absent:- \n(Enter the number \"0\" when done)");
			int is_absent[] = new int[total_stud+1];	//Creates an array where the absent roll numbers will be stored
			int sort_abs[] = new int[total_stud+2];	//This array is created for sorting roll numbers to ascending order and its one number greater than original array to avoid ArrayIndexOutOfBoundsException while sorting
			int j=0,abs_count=0;
			for(int i=1;i<=total_stud;i++)
			{
				is_absent[j] = sc.nextInt();
				if(is_absent[j]>0 && is_absent[j]<=total_stud)
				{
					abs_count++;
				}
				if(is_absent[j]==0)
					break;
				if(is_absent[j]<0)
					System.out.println("You entered a value which is -ve or 0.");
				if(is_absent[j]>total_stud)
					System.out.println("Max Students are "+total_stud+".");
				if(is_absent[j]!=0 && is_absent[j]<=total_stud)
					j++;
			}
			sort_abs = is_absent;	//COPY ARRAY ELEMENTS TO sort_abs
			j=0;
			int s=0;
			int temp=0;
			int error = 0;
			for(int i=0;i<=abs_count;i++)
			{
				if(error==1)
					break;
				if(sort_abs[s+1]==0)	//As we know, other elements will be 0. So to avoid them, the loop will break once it reaches the element 0
					break;
				if(is_absent[j]==sort_abs[s+1])
				{
					System.out.println("Warning:- Duplicate Roll numbers have been detected... The Attendance you have created might not display correctly.");
					error = 1;

				}
				j++;
				s++;
				j=0;
				s=0;
			}
			j=0;
			s=0;
			temp=0;
			for(int i=1;i<=total_stud;i++) //Sorting Absent roll numbers
			{
				for(int k=1;k<=total_stud;k++)
				{
					if(sort_abs[s+1]==0)	//As we know, other elements will be 0. So to avoid them, the loop will break once it reaches the element 0
						break;
					if(is_absent[j] > sort_abs[s+1])
					{
						temp = is_absent[j];
						is_absent[j] = sort_abs[s+1];
						sort_abs[s+1] = temp;
					}
					j++;
					s++;
				}
				j=0;
				s=0;
			}
			j=0;
			outputStream.print("Absent Students List :- ");
			for(int i=1;i<=total_stud;i++)
			{
				if(is_absent[j]==0)
					break;
				outputStream.print(is_absent[j]+" ");
				j++;
			}
			outputStream.println("\n\nAbsent Students Count :- "+abs_count);	//Printing Absent Students Count
			String status;		
			outputStream.println("\nAttendance-wise :- ");	//Printing all Roll Numbers Attendance-wise starts from here
			j=0;
			for(int i=1;i<=total_stud;i++)
			{
				if(is_absent[j]!=i)
					status = "Present";
				else
					status = "Absent";
				outputStream.println("Roll No. "+i+" :- "+status);
				if(is_absent[j]!=i)
					j--;
				j++;
			}
			outputStream.close();			
			System.out.print("Done... ");
		}
		catch(Exception e){
			System.out.println("\nSorry but, something went wrong... Exiting \"Attendance Creation\"...");
		}
	}
}
class attendance
{
	public static void main(String args[])
	{
		char case1 = '\u0000';	//Default value of char is '\u0000'
		String fileName;	//fileName for creating Attendance files
		Scanner sc = new Scanner(System.in);
		read r1 = new read();
		write w1 = new write();
		dateValidation dv = new dateValidation();
		System.out.println("\nWelcome to a new JAVA project \"Attendance Management System\"");
		while(case1!='3')
		{
			System.out.println("\n=========[ ATTENDANCE MANAGEMENT SYSTEM ]=========");
			System.out.println("1. Create New Attendance\n2. Display Existing Attendance\n3. Exit (or press Ctrl+C if \"3\" is not working)");
			System.out.print("Pick your choice:- ");
			case1 = sc.next().charAt(0);
			switch(case1)
			{
				case '1':
					System.out.print("\nYou chose to Create New Attendance... ");
					System.out.print("Enter the date you want to create attendance on. Use DD-MM-YYYY format only :- ");
					fileName = sc.next();
					boolean isValid = dv.validateDate(fileName);
					if( isValid)
						w1.m_write(fileName);
					else	
						System.out.println("Invalid date");
				break;

				case '2':
					System.out.print("\nYou chose to Display existing Attendance... ");
					System.out.print("Enter the date you want to display attendance of. Use DD-MM-YYYY format only :- ");
					fileName = sc.next();
					isValid = dv.validateDate(fileName);
					if( isValid)
						r1.m_read(fileName);
					else	
						System.out.println("Invalid date");
				break;

				case '3':
					System.out.print("\nYou chose to Exit the program... ");
					System.out.println("Program Terminated... ");
				break;

				default	:
					System.out.print("\nInvalid Input!! Try again.. ");
			}
		}
		sc.close();		
	}
}