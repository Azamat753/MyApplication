package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.example.myapplication.MainActivity.CHANNEL_ID;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;


public class ForegroundService extends Service {
    private static final int NOTIFY_ID = 101;
    Random random;

public static final int THIRD_HOUR=10000*60*180;
    private NotificationManager notificationManager;

    List<String> messages = new ArrayList<>();
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private Timer timer;
    final int REFRESH = 0;
    Context context;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messages.add("to Study\n" +
                "Изучать");
        messages.add("I study English \n" +
                "Я изучаю английский язык");
        messages.add("It is easy to study \n" +
                "Учиться легко");
        messages.add("I enjoy studying English\n" +
                "Я с удовольствием занимаюсь английским");
        messages.add("I will study hard\n" +
                "Я буду учиться усердно");
//        messages.add("Повтори изученное:\n" +
//                "\n" +
//                "… - Учиться легко \n" +
//                "to Study - …\n" +
//                "… - Я изучаю английский язык\n" +
//                "… - Я буду учиться усердно\n" +
//                "I enjoy studying English - …");
        messages.add("Happy \n" +
                "Счастливый, довольный");
        messages.add("I am happy person\n" +
                "Я счастливый человек");
        messages.add("I am not quite happy witch your idea \n" +
                "Я не в восторге от вашей идеи");
        messages.add("You don’t look very happy today \uD83D\uDE41\n" +
                "Ты сегодня какой-то невеселый");
        messages.add("I am very happy for you \n" +
                "Я так рад за вас");
        messages.add("The story has a happy ending\uD83D\uDE3B\n" +
                "У этой истории счастливый конец");
        messages.add("To be as happy as the day is long \n" +
                "Быть очень счастливым");
//        messages.add("Повтори изученное:\n" +
//                "\n" +
//                "… - Я не в восторге от вашей идеи\n" +
//                "I am happy person - …\n" +
//                "Happy - …\n" +
//                "… - Быть очень счастливым\n" +
//                "… - Ты сегодня какой-то невеселый\n" +
//                "The story has a happy ending - …\n" +
//                "… - Я так рад за вас");
        messages.add("to Know\n" +
                "знать");
        messages.add("As far as I know\n" +
                "Насколько я знаю");
        messages.add("Do you know him? \n" +
                "Ты его знаешь?");
        messages.add("How do you know?\n" +
                "Откуда ты знаешь?");
        messages.add("Let me know\n" +
                "Дай мне знать");
        messages.add("Who knows?\n" +
                "Кто знает/как знать?");
        messages.add("You never know\n" +
                "Никогда не знаешь...");
        messages.add("Comfortable \n" +
                "удобный, уютный, расслабленный");
        messages.add("Are you comfortable?\n" +
                "Вам так удобно?");
        messages.add("Are you comfortable witch this decision? \n" +
                "Тебя это решение устраивает?");
        messages.add("He has a comfortable job\n" +
                "У него приличная работа");
        messages.add("I live in a very comfortable apartment \n" +
                "Я живу в очень уютной квартире");
        messages.add("I never feel comfortable in her presence \n" +
                "Я чувствую себя неловко в ее присутствии");
        messages.add("Make yourself comfortable \n" +
                "Устраивайтесь поудобнее");
        messages.add("My new shoes are not very comfortable \n" +
                "Мои новые ботинки не очень удобные");
        messages.add("Wrong \n" +
                "Неправильный");
        messages.add("Correct me if I’m wrong\n" +
                "Поправь, если я не прав");
        messages.add("Don’t get me wrong \n" +
                "Пойми меня правильно");
        messages.add("It’s a wrong answer\n" +
                "Это неправильный ответ");
        messages.add("My watch is wrong\n" +
                "Мои часы иду неправильно");
        messages.add("You are wrong!\n" +
                "Ты неправ!");
        messages.add("You look serious. What’s wrong?\n" +
                "Ты сегодня какой-то серьезный. Что-то случилось?");
        messages.add("You shirt is on the wrong side \n" +
                "У тебя рубашка наизнанку");
        messages.add("to Understand \n" +
                "Понимать");
        messages.add("As far as I understand \n" +
                "Насколько я понимаю");
        messages.add("Can you understand English?\n" +
                "Вы понимаете по-английски?");
        messages.add("I don’t understand you\n" +
                "Я Вас не понимаю");
        messages.add("It’s easy to understand \n" +
                "Это легко понять");
        messages.add("I understand you \n" +
                "Я Вас понимаю");
        messages.add("She understood everything by his eyes \n" +
                "Она поняла все по его глазам");
        messages.add("Sorry, I misunderstood you\n" +
                "Извините, я Вас неправильно понял");
        messages.add("to Talk \n" +
                "Разговаривать");
        messages.add("I don’t understand what you are talking about \n" +
                "Я не понимаю, о чем ты говоришь");
        messages.add("Let’s not talk about that \n" +
                "Давай не будем говорить об этом!");
        messages.add("Stop talking and listen!\n" +
                "Перестань болтать и послушай!");
        messages.add("Talk is cheap. Until you hire a lawyer. \n" +
                "Разговор - это дёшево. Пока не наймёшь адвоката");
        messages.add("Talk to me \uD83D\uDE4F\uD83C\uDFFC\n" +
                "Поговори со мной");
        messages.add("We aren’t talking to each other \n" +
                "Мы не разговариваем друг с другом");
        messages.add("We need to talk \n" +
                "Нам нужно поговорить");
        messages.add("Time \n" +
                "Время, раз");
        messages.add("Have a good time \uD83E\uDD73\uD83D\uDC83\uD83C\uDFFC\uD83D\uDD7A\uD83C\uDFFC\n" +
                "Желаю хорошо провести время");
        messages.add("I am having a hard time\n" +
                "У меня сейчас трудный период");
        messages.add("It’s a waste of time \n" +
                "Это пустая трата времени");
        messages.add("It’s time to go\n" +
                "Пора идти");
        messages.add("I won’t take any more of your time\n" +
                "Не буду больше отнимать у Вас время");
        messages.add("See you next time\n" +
                "Увидимся в следующий раз");
        messages.add("Take your time\n" +
                "Не спешите");
        messages.add("Time doesn’t exist. Clock exist.\n" +
                "Времени не существует. Часы существуют");
        messages.add("Time heals \n" +
                "Время лечит");
        messages.add("What time is it?\n" +
                "Который сейчас час?");
        messages.add("Your time is up\n" +
                "Ваше время истекло");
        messages.add("Breakfast \n" +
                "Завтрак");
        messages.add("Do you want bacon and eggs for breakfast?\n" +
                "Будешь яичницу с беконом на завтрак?");
        messages.add("I usually have a big breakfast \n" +
                "Мой завтрак обычно плотный");
        messages.add("Let’s have breakfast together \uD83D\uDE09\n" +
                "Давай позавтракаем вместе");
        messages.add("She doesn’t eat much for breakfast \n" +
                "Она не много ест на завтрак");
        messages.add("What did you talk about at breakfast?\n" +
                "О чем вы говорили за завтраком?");
        messages.add("What do you normally have for breakfast? \n" +
                "Что ты обычно ешь на завтрак?");
        messages.add("to Take \n" +
                "Брать, хватать");
        messages.add("Can I take your pen?\n" +
                "Можно взять твою ручку?");
        messages.add("Don’t take is close to your heart \n" +
                "Не принимай это близко к сердцу");
        messages.add("How did he take it?\n" +
                "Как он к этому отнёсся?");
        messages.add("It takes me 20 min to get home \uD83C\uDFE1 \n" +
                "Дорога домой занимает 20 минут");
        messages.add("Let me take your coat \uD83E\uDDE5 \n" +
                "Позвольте взять Ваше пальто");
        messages.add("Take a look at ti! \uD83D\uDC40 \n" +
                "Посмотри-ка на него!");
        messages.add("Take your seats \n" +
                "Занимайте свои места/садитесь");
        messages.add("Take your time \n" +
                "Не спешите");
        messages.add("What took your so long? \n" +
                "Почему ты так долго?");
        messages.add("Weather\n" +
                "Погода");
        messages.add("I go running in all weathers \uD83C\uDFC3 \n" +
                "Я бегаю в любую погоду");
        messages.add("I’m under the weather \uD83E\uDD27\n" +
                "Я плохо себя чувствую");
        messages.add("The weather fined a bit\n" +
                "Прояснилось");
        messages.add("The weather is nice today ☀️ \n" +
                "Сегодня хорошая погода");
        messages.add("What’s the weather forecast for tomorrow?\n" +
                "Какой прогноз погоды на завтра?");
        messages.add("What terrible weather!\n" +
                "Какая ужасная погода!");
        messages.add("Mad\n" +
                "Злой, бешеный");
        messages.add("Are you still mad at me?\n" +
                "Ты все ещё злишься на меня?");
        messages.add("I am mad about you\n" +
                "Я без ума от тебя");
        messages.add("I am mad at myself!\n" +
                "Я зол на себя!");
        messages.add("That noise is driving me mad\n" +
                "Меня раздражает этот шум");
        messages.add("We hat a mad time in that club\n" +
                "В том клубе было безумно весело");
        messages.add("Why are you so mad?\n" +
                "Ты чего такой злой?");
        messages.add("You make me so mad!\n" +
                "Ты меня так бесишь!");
        messages.add("to Enjoy\n" +
                "Наслаждаться, любить");
        messages.add("Did you enjoy the film?\n" +
                "Вам понравился фильм?");
        messages.add("Enjoy your meal \n" +
                "Приятного аппетита");
        messages.add("Do you enjoy the theatre?\n" +
                "Вы любите театр?");
        messages.add("I enjoy learning English \n" +
                "Я с удовольствием изучаю английский");
        messages.add("I enjoy my job\n" +
                "Я люблю свою работу");
        messages.add("I really enjoyed it!\n" +
                "Мне это очень понравилось");
        messages.add("Relax and enjoy view \n" +
                "Расслабьтесь и наслаждайтесь видом");
        messages.add("Kind\n" +
                "Добрый, тип");
        messages.add("He is a very kind man\n" +
                "Он очень добрый человек");
        messages.add("I enjoy all kind of music \uD83C\uDFB6 \n" +
                "Я люблю разную музыку");
        messages.add("It is very kind of you!\n" +
                "Это очень мило с вашей стороны!");
        messages.add("Nothing of the kind!\n" +
                "Ничего подобного!");
        messages.add("What kind of films do you like? \n" +
                "Какие фильмы ты любишь?");
        messages.add("You are very kind to me \n" +
                "Вы очень добры ко мне");
        messages.add("Same \n" +
                "Такой же, одинаковый");
        messages.add("All of them spoke at the same time\n" +
                "Они все говорили одновременно");
        messages.add("I have the same problem\n" +
                "У мены такая же проблема");
        messages.add("It happened in the same day\n" +
                "Это случилось в тот же день");
        messages.add("Same to you!\n" +
                "И тебе того же!");
        messages.add("Just the same\n" +
                "Все равно");
        messages.add("You’ll never think the same of me again\n" +
                "Ты никогда не будешь думать обо мне так, как прежде");
        messages.add("Way\n" +
                "Путь, способ, образ действий");
        messages.add("Are you going my way?\n" +
                "Нам по пути?");
        messages.add("Can you tell me the way to the center?\n" +
                "Вы не подскажете, как добраться до центра?");
        messages.add("I like the way you do it\n" +
                "Мне нравится то, как ты делаешь это");
        messages.add("I met him on my way home\n" +
                "Я встретил его по пути домой");
        messages.add("No way!\n" +
                "Ни за что на свете!/Не может быть!");
        messages.add("She smiled in a friendly way\uD83D\uDE43\n" +
                "Она дружелюбно улыбнулась");
        messages.add("We will find a way to do it!\n" +
                "Мы найдём способ сделать это!");
        messages.add("Care \n" +
                "Забота, беспокоиться");
        messages.add("Be careful!\n" +
                "Аккуратней!");
        messages.add("He cares only for himself\n" +
                "Он заботится только о себе");
        messages.add("He is a careful driver\n" +
                "Он внимательный водитель");
        messages.add("I don’t care!\n" +
                "Меня это не волнует!/Мне все равно!");
        messages.add("She is a caring wife \n" +
                "Она заботливая жена");
        messages.add("Take care!\n" +
                "Береги себя!(прощание)");
        messages.add("Who cares?\n" +
                "Кому какое дело?");
        messages.add("Suit\n" +
                "Подходить, костюм");
        messages.add("He had a black suit on\n" +
                "На нем был чёрный костюм");
        messages.add("It suits me\n" +
                "Это мне подходит");
        messages.add("Red suit you \n" +
                "Красный тебе идёт");
        messages.add("This suits my needs\n" +
                "Это подходит моим требованиям");
        messages.add("This time doesn’t suit me\n" +
                "Это время мне не подходит");
        messages.add("Your own attire, is it really suitable?\n" +
                "Вот ваш наряд, вы правда считаете его уместным?");
        messages.add("suit yourself\n" +
                "Как хочешь, дело твоё");
        messages.add("to Recognize \n" +
                "Узнать, признать, распознать");
        messages.add("He has recognizable face \n" +
                "У него запоминающееся лицо");
        messages.add("I can’t recognize anyone in this photo\n" +
                "Я не могу узнать никого на этой фотографии");
        messages.add("I didn’t recognize you!\n" +
                "Я не узнал Вас!");
        messages.add(" had to recognize he was right\n" +
                "Мне пришлось признать, что он был прав");
        messages.add("My work is not recognized by anybody!\n" +
                "Моя работа никем не признана!");
        messages.add("You will recognize me by my black hat\n" +
                "Вы узнаете меня по моей чёрной шляпе");
        messages.add("to Leave \n" +
                "Покидать, оставлять, уходить");
        messages.add("Every day I leave home at 8 am \n" +
                "Каждый день я выхожу из дома в 8 утра");
        messages.add("Five minutes left\n" +
                "Осталось пять минут");
        messages.add("It’s time to leave\n" +
                "Пора уходить");
        messages.add("I’ve left my watch at home \n" +
                "Я забыл свои часы дома");
        messages.add("I want you to leave \n" +
                "Я хочу, чтобы ты ушёл");
        messages.add("Leave the door open \uD83D\uDEAA \n" +
                "Оставь дверь открытой");
        messages.add("Please, don’t leave me!\n" +
                "Пожалуйста, не покидайте меня");
        messages.add("Matter \n" +
                "Дело, вопрос; иметь значение");
        messages.add("I’d rather let the matter drop\n" +
                "Я бы предпочёл больше это не обсуждать");
        messages.add("It doesn’t matter\n" +
                "Это не имеет значения");
        messages.add("It is a matter of time \n" +
                "Это вопрос времени");
        messages.add("It is a matter of a few months \n" +
                "Это вопрос нескольких месяцев");
        messages.add("The matter allows of no delay \n" +
                "Дело не терпит отлагательства");
        messages.add("What’s the matter?\n" +
                "В чем дело?");
        messages.add("What’s the matter with you? \n" +
                "Что с тобой?");
        messages.add("Support \n" +
                "Поддержка");
        messages.add("All we can do is offer her our support \n" +
                "Все, что мы можем сделать, это предложить ей нашу поддержку");
        messages.add("He supports his family \n" +
                "Он содержит свою семью");
        messages.add("His proposal got support from the whole team \n" +
                "Его предложение поддержала вся команда");
        messages.add("Support bacteria- they’re the only culture some people have\n" +
                "Поддерживайте бактерии - это единственная культура, которая есть у некоторых людей");
        messages.add("What team do you support?\n" +
                "За какую команду вы болеете?");
        messages.add("Who supports this idea? \n" +
                "Кто поддерживает эту идею?");
                messages.add("Who supports this idea? \n" +
                        "Кто поддерживает эту идею?");



        context = this;
        random = new Random();

        TimerTask refresher;

        timer = new Timer();
        refresher = new TimerTask() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.scheduleAtFixedRate(refresher, 1, 1000*60*120);

    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH:
                    scheduler.scheduleWithFixedDelay(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            for (int i = 0; i < random.nextInt(messages.size()); i++) {
                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                                .setSmallIcon(R.drawable.english_logo)
                                                .setContentTitle("Напоминание")
                                                .setDefaults(Notification.DEFAULT_SOUND)
                                                .setAutoCancel(true)
                                                .setOnlyAlertOnce(true).setStyle(new NotificationCompat
                                                .BigTextStyle().bigText(messages.get(i)));

                                notificationManager = getSystemService(NotificationManager.class);
                                notificationManager.notify(NOTIFY_ID, builder.build());
                            }
                        }
                    }, 0, 3, HOURS);
                    break;
//                default:
//                    break;
            }
        }
    };
}
