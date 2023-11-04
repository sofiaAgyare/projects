import structure5.*;


public class Student implements Comparable <Student> {

/**
 * name of student
 */
protected String name;
/**
 * stores the 4 classes the student is taking
 */
protected Vector<String> classes = new Vector<String>();
/**
 * stores the slots that make up the exam schedule of the student
 */
protected Vector<Association<Integer, String>> studentSchedule;


/**
 * student constructor
 * @param stu array of student information
 */
public Student(String [] stu){
    this.name = stu[0];
    for (int i = 1; i <5; i++){
        this.classes.add(stu[i]);
    }
}

/**
 * creates the student's schedule
 * @param s the slots they must attend
 */
public void studentSchedule(Vector<Association<Integer, String>> s){
    this.studentSchedule = s;
    //System.out.println(name + " " + studentSchedule);

}

/**
 * comparator for students
 * @param stu student object
 * @return difference between two student objects
 */
public int compareTo(Student stu){
    return (this.name.compareTo(stu.name));
}


}
