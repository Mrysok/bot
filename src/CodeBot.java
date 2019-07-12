import java.util.*;
import java.util.regex.*;

class CodeBot {
	Pattern pattern;
	Random random;
	Date date;
	String f2;
	
	final String[]COMMON_PHRASES = {
			"Я не понимаю чтоты хочешь от меня.",
			"Чтоты хочешь от меня.",
			"Я не понимаю "
	};
	
	final String[] ELUSIVE_ANSWERS = {
			"Глупый вопрос."
	};
	
	final Map<String,String> PATTERNS_FOR_ANALYSIS = new HashMap<String,String>(){{
		//Приветствие
		put("хай","hello");
		put("привет","hello");
		put("Здравствуй","hello");
		put("дратути","hello");
		//кто ты
		put("кто\\s.*ты","who");
		put("ты\\s.*кто","who");
		//имя
		put("как\\s.*имя","name");
		put("как\\s.*зовут","name");
		put("какое\\s.*имя","name");
		//как ты
		put("как\\s.*дела","howareyou");
		//занятие
		put("что\\s.*делаешь","whatareyoudoing");
		put("чем\\s.*занимаешься","whatareyoudoing");
		//согласие
		put("^да","yes");
		put("согласен","yes");
		//отрицание
		put("^нет","no");
		//время
		put("сколько\\s.*время","time");
		put("время\\s.*сколько","time");
		//прощание
		put("пока","bye");
	}};
	
	final Map<String,String> ANSWERS_BY_PATTERNS = new HashMap<String,String>(){{
		put("hello","Ну привет.");
		put("who","Обычный чат-бот.");
		put("name","Зовите меня Чаттинг :)");
		put("howareyou","Все отлично.");
		put("whatareyoudoing","Пытаюсь равлечь тебя!");
		put("bye","До встречи!");
		put("yes","Да!");
		put("no","Нет!");
	}};
	
	CodeBot(){
		random = new Random();
		date = new Date();
	}
	String sayInReturn(String msg, boolean ai){
		String say = (msg.trim().endsWith("?"))?//проверка на вопрос
				ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
				COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];//сравнивание ключей для фраз которые бот не понимает
		if(ai){
			String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
			for(Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()){//цикл проходящий до конца карты
				pattern = Pattern.compile(o.getKey());
				if(pattern.matcher(message).find())//проверка на вывод ответа
					if(o.getValue().equals("time"))return date.toString(); //проверка на вывод даты/время
					else return ANSWERS_BY_PATTERNS.get(o.getValue());}//возврат в main ответа чат-бота с датой
		}
		return say;//возврат в main ответа чат-бота
	}
}
 