import structure5.*;
import java.util.Scanner;
import java.util.Iterator;


/**
 * this program willl create an exam schedule for students taking for classes,
 * in which different slots of exams for a class don't overlap for any student
 */

public class ExamScheduler {
    /**
     * graph representing all the classes taken by the students
     */
    protected GraphListUndirected<String, String> graph;
    /**
     * vector of the students' information 
     */
    protected OrderedVector<Student> students;
    /**
     * final schedule of all the slots contating non-connected classes
     */
    protected Vector<Vector<String>> schedule;

    /**
     * constructor initializes the graph, student information, and shedule
     */
    public ExamScheduler() {
        graph = new GraphListUndirected<String, String>();
        students = new OrderedVector<Student>();
        schedule = new Vector<Vector<String>>();
    }

    /**
     * helper method checks if a class has a student in common with any of the classes
     * in a slot
     * @param slot
     * @param s class to be checked for common student
     * @return true/false if there is a student in common
     */
    public boolean isConnected(Vector<String> slot, String s){
        for (String i: slot){
            if (graph.containsEdge(s, i)){
                return true;
            }
        }
        return false;
    }

    /**
     * builds the schedule by organizing all the students' classes
     * into slots that don't have any students in common
     */
    public void slot(){
        while (!graph.isEmpty()){
            Vector<String> slot = new Vector<>();
            //Iterator<String> itr = graph.iterator();
            //String first = itr.next();
            //slot.add(first);
            for (String i: graph){
                if (!isConnected(slot, i)){
                    slot.add(i);
                }
            }
            schedule.add(slot);
            for (String i: slot){
                graph.remove(i);
            }
        }
    }

    /**
     * scans in the information of the students and the classes their
     * taking to build the schedule with
     */
    public void readIn() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine())
            String[] stu = new String[5];
            for (int i = 0; i < 5; i++) {
                String line = in.nextLine();
                stu[i] = line;
            }
            Student s = new Student(stu);
            students.add(s);
        }
        /*for (Student i: students){
            System.out.println(i.name);
            System.out.println(i.classes);
        }
        */
    }

    /**
     * populates the graph based off the information of the students
     */
    public void populate() {
        for (Student i: students){
            for (String j: i.classes){
                graph.add(j);
                for (String k: i.classes){
                    graph.add(k);
                    if (j.equals(k)){
                        continue;
                    }
                    graph.addEdge(j, k, j + " " + k);
                }
            }
        }
    }

    /**
     * generates a schedule for each student
     */
    public void genSchedules(){
        for (Student stu : students){
            /**
             * vector idx will store associations of < the slot number (that corresponds
             * to the time slot when the student's) class (will be held)>
             */
            Vector<Association<Integer, String>> idx = new Vector<Association<Integer, String>>();
            for (String clas: stu.classes){
                for (int j = 0; j < schedule.size(); j++){
                    if (schedule.get(j).contains(clas)){
                        Association<Integer, String> oneClas = new Association<Integer,String>(j, clas);
                        idx.add(oneClas);
                    }
                }
            }
            //creates the student's schedule in the Student class
            stu.studentSchedule(idx);
            
        }
    }

    /**
     * prints the schedule for each student and which exam slots they must attend
     */
    public void printStudentSchedules(){
        for (Student i: students){
            System.out.println(i.name + " Schedule: ");
            for (Association<Integer, String> j : i.studentSchedule){
                System.out.println("Attend Slot " + (j.getKey()+1) + " for: " + j.getValue());
            }
        }
    }

    /**
     * prints all the slots of the finilized schedule
     */
    public void printSchedule(){
        for (int i = 0; i < schedule.size(); i++){
            System.out.println("Slot " + (i+1) + ": " + schedule.get(i));
        }
    }

    /**
     * main creates exam schedules
     * @param args
     */
    public static void main(String[] args){
        ExamScheduler work = new ExamScheduler();
        work.readIn();
        work.populate();
        work.slot();
        work.printSchedule();
        work.genSchedules();
        work.printStudentSchedules();
    }



    //extra testing print methods

    /**
     * prints graph's nodes
     */
    public void printNodes(){  
        for (String i: graph){
          System.out.println("class " + i);
          Iterator<String> itr = graph.neighbors(i);
          while (itr.hasNext()){
              System.out.println(itr.next());
          }
          System.out.println(" ");
        }
  
      }
  
      /**
       * prints graph's edges
       */
      public void printEdges(){
          Iterator<Edge<String,String>> itr = graph.edges();
          while (itr.hasNext()){
              System.out.println(itr.next());
          }
      }

}