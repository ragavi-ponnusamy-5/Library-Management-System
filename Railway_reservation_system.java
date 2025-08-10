import java.util.*;
class Passenger{
    private final String name; // private because of encapsulation,
    private final int age;// final-does not change
    private final int seat_number;
    //constructor
    Passenger(String name,int age,int seat_number){
        this.name=name;
        this.age=age;
        this.seat_number=seat_number;
    }
    //encapsulation
    String getName(){
        return name;
    }
    int getAge(){
        return age;
    }
    int getSeatNumber(){
        return seat_number;
    }
}
//abstraction
abstract class Reservation{
    protected static final int total_seats=10;
    protected static final boolean available[]=new boolean[total_seats+1]; // 1-Occupied ,0-free | 1 based indexing
    protected final List<Passenger> passengerList =new ArrayList<>();

    //abstract methods
    protected abstract void Booking(String name,int age);
    protected abstract void canceling(int seat_number);
    protected abstract void display() ;

    //allocating seat numbers
    int allocate_seats(){
        for(int i=1;i<=total_seats;i++){
            if(!available[i]){ // if 0
                available[i]=true;
                return i;
            }
        }
        return -1; // if no seats available
    }

    //cancelling

    void freeup_seats(int seat_no){
        if(seat_no>=1 && seat_no<=10){
            available[seat_no]=false; //0-free
        }
    }
}
//Inheritance

class SleeperClass extends Reservation{
     private static final int fare=500;

    @Override
     public void Booking(String name,int age){
         int seat_num=allocate_seats();
         if(seat_num!=-1){
             passengerList.add(new Passenger(name,age,seat_num));
             System.out.printf("Sleeper class booked.\nSeat Number:%d\nFare:%d\n",seat_num,fare);
         }
         else{
             System.out.print("No seats available.\n");
         }
     }

    @Override
     public void canceling(int seat_num){
         for(Passenger p:passengerList){
             if(p.getSeatNumber()==seat_num){
                 passengerList.remove(p);
                 freeup_seats(seat_num);
                 System.out.printf("Sleeper class ticket is cancelled.\nSeat Number:%d",seat_num);
                 return;
             }
         }
         System.out.print("No booking available for the seat in Sleeper Class");
     }

    @Override
    public void display(){
        if (passengerList.isEmpty()) {
            System.out.println("No Sleeper Class Bookings.");
        }
        else {
            for(Passenger p:passengerList){
                System.out.printf("Name:%s | Age:%d | SeatNumber:%d\n",p.getName(),p.getAge(),p.getSeatNumber());
            }
        }
    }
}

 class ACclass extends Reservation{
    private static final int fare=1500;
    @Override
    public void Booking(String name,int age){
        int seat_num=allocate_seats();
        if(seat_num!=-1){
            passengerList.add(new Passenger(name,age,seat_num));
            System.out.printf("AC class booked.\nSeat Number:%d\nfare:%d\n",seat_num,fare);
        }
        else {
            System.out.print("No seats available.\n");
        }
    }
    @Override
    public void canceling(int seat_num){
        for(Passenger p:passengerList){
            if(p.getSeatNumber()==seat_num){
                passengerList.remove(p);
                freeup_seats(seat_num);
                System.out.printf("AC class ticket is cancelled.\nSeat Number:%d",seat_num);
                return;
            }
        }
        System.out.print("No booking available for the seat in AC Class");
    }
    @Override
    public void display(){
        if (passengerList.isEmpty()) {
            System.out.println("No AC Class Bookings.");
        }
        else {
            for(Passenger p:passengerList){
                System.out.printf("Name:%s | Age:%d | SeatNumber:%d\n",p.getName(),p.getAge(),p.getSeatNumber());
            }
        }
    }
 }

 class GeneralClass extends Reservation{
    private static final int fare=800;
    @Override
    public void Booking(String name,int age){
        int seat_num=allocate_seats();
        if(seat_num!=-1){
            passengerList.add(new Passenger(name,age,seat_num));
            System.out.printf("Reservation class booked.\nSeat Number:%d\nfare:%d\n",seat_num,fare);
        }
        else {
            System.out.print("No seats available.\n");
        }
    }
    @Override
    public void canceling(int seat_num){
        for(Passenger p: passengerList){
            if(p.getSeatNumber()==seat_num){
                passengerList.remove(p);
                freeup_seats(seat_num);
                System.out.printf("General class ticket is cancelled.\nSeat Number:%d",seat_num);
                return;
            }
        }
        System.out.print("No booking available for the seat in General Class");
    }
    @Override
    public void display(){
         if (passengerList.isEmpty()) {
             System.out.println("No General Class Bookings.");
         }
         else {
             for(Passenger p:passengerList){
                 System.out.printf("Name:%s | Age:%d | SeatNumber:%d\n",p.getName(),p.getAge(),p.getSeatNumber());
             }
         }
     }
 }

//main class
class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Reservation sleeper=new SleeperClass();
        Reservation ac=new ACclass();
        Reservation general=new GeneralClass();

        while (true){
            System.out.print("\nTrain Reservation Menu\n");
            System.out.print("1.Book Tickets\n");
            System.out.print("2.Cancel Tickets\n");
            System.out.print("3.Display Bookings\n");
            System.out.print("4.Exit\n");
            System.out.print("Choose an option:");
             int choice =sc.nextInt();
             switch(choice){
                 case 1:
                     System.out.println("Select class 1.Sleeper Class 2.AC class 3.General  class");
                     int class_choice=sc.nextInt();
                     sc.nextLine();
                     System.out.print("Enter Name:");
                     String name=sc.nextLine();
                     System.out.print("Enter Age:");
                     int age=sc.nextInt();

                     if(class_choice==1)
                         sleeper.Booking(name,age);
                     else if(class_choice==2)
                         ac.Booking(name,age);
                     else if(class_choice==3)
                         general.Booking(name,age);
                     else System.out.println("Invalid class selection.");
                     break;

                 case 2:
                     System.out.println("Select class 1.Sleeper Class 2.AC class 3.General  class");
                     int cancel_choice=sc.nextInt();
                     sc.nextLine();
                     System.out.print("Enter Seat Number to cancel:");
                     int seat_num=sc.nextInt();

                     if(cancel_choice==1)
                         sleeper.canceling(seat_num);
                     else if(cancel_choice==2)
                         ac.canceling(seat_num);
                     else if(cancel_choice==3)
                         general.canceling(seat_num);
                     else System.out.println("Invalid class selection.");
                     break;

                 case 3:
                     sleeper.display();
                     ac.display();
                     general.display();
                     break;
                 case 4:
                     System.out.println("Exiting....");
                     return;

                 default:
                     System.out.println("Invalid option");
             }

        }
    }
}
