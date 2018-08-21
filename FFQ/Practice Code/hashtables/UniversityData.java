package hashtables;

import java.util.ArrayList;

public class UniversityData {
	
	private class Student {
		int id;
		String name;
		
		public Student(String s, int i) {
			this.id = i;
			this.name = s;
		}
	}
	
	private class StudentHashMap {
		Student[] students = new Student[61];
		
		public void add(Student stu) {
			students[stu.id] = stu;
		}
		
		public void remove(Student stu) {
			students[stu.id] = null;
		}
		
		public Student get(int id) {
			return students[id];
		}

	}
	
	static ArrayList<StudentHashMap> allYears = new ArrayList<>();
	
	public static void main(String[] args) {
		
	}
}
