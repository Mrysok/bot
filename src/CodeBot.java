import java.util.*;
import java.util.regex.*;

class CodeBot {
	Pattern pattern;
	Random random;
	Date date;
	String f2;
	
	final String[]COMMON_PHRASES = {
			"� �� ������� ����� ������ �� ����.",
			"����� ������ �� ����.",
			"� �� ������� "
	};
	
	final String[] ELUSIVE_ANSWERS = {
			"������ ������."
	};
	
	final Map<String,String> PATTERNS_FOR_ANALYSIS = new HashMap<String,String>(){{
		//�����������
		put("���","hello");
		put("������","hello");
		put("����������","hello");
		put("�������","hello");
		//��� ��
		put("���\\s.*��","who");
		put("��\\s.*���","who");
		//���
		put("���\\s.*���","name");
		put("���\\s.*�����","name");
		put("�����\\s.*���","name");
		//��� ��
		put("���\\s.*����","howareyou");
		//�������
		put("���\\s.*�������","whatareyoudoing");
		put("���\\s.*�����������","whatareyoudoing");
		//��������
		put("^��","yes");
		put("��������","yes");
		//���������
		put("^���","no");
		//�����
		put("�������\\s.*�����","time");
		put("�����\\s.*�������","time");
		//��������
		put("����","bye");
	}};
	
	final Map<String,String> ANSWERS_BY_PATTERNS = new HashMap<String,String>(){{
		put("hello","�� ������.");
		put("who","������� ���-���.");
		put("name","������ ���� ������� :)");
		put("howareyou","��� �������.");
		put("whatareyoudoing","������� ������� ����!");
		put("bye","�� �������!");
		put("yes","��!");
		put("no","���!");
	}};
	
	CodeBot(){
		random = new Random();
		date = new Date();
	}
	String sayInReturn(String msg, boolean ai){
		String say = (msg.trim().endsWith("?"))?//�������� �� ������
				ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
				COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];//����������� ������ ��� ���� ������� ��� �� ��������
		if(ai){
			String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
			for(Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()){//���� ���������� �� ����� �����
				pattern = Pattern.compile(o.getKey());
				if(pattern.matcher(message).find())//�������� �� ����� ������
					if(o.getValue().equals("time"))return date.toString(); //�������� �� ����� ����/�����
					else return ANSWERS_BY_PATTERNS.get(o.getValue());}//������� � main ������ ���-���� � �����
		}
		return say;//������� � main ������ ���-����
	}
}
 