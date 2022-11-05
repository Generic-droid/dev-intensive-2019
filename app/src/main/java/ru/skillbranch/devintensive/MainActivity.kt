package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var benderObj: Bender

    /**
     * Вызывается при первом создании или запуске Activity.
     *
     * здесь задаётся внешний вид активности (UI) через метод setContentView.
     * инициализируются представления
     * представления связываются с необходимыми данными и ресурсами
     * связываются данные со списками
     *
     * Этот метод также предоставляет Bundle, содержащий ранее сохранённые
     * данные для восстановления состояния Activity, если они были.
     *
     * Всегда сопровождается вызовом onStart().
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv_bender = findViewById<ImageView>(R.id.iv_bender)
        val tv_text = findViewById<TextView>(R.id.tv_text)
        val et_message = findViewById<EditText>(R.id.et_message)
        val iv_send = findViewById<ImageView>(R.id.iv_send)

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val message = savedInstanceState?.getString("ANSWER") ?: ""
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question) )

        Log.d("M_MainActivity", "onCreate() $status $question $message")

        val (r, g, b) = benderObj.status.color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        tv_text.text = benderObj.askQuestion()

        et_message.setText(message)

        iv_send.setOnClickListener(this)

        et_message.setOnEditorActionListener { textView, i, keyEvent ->

            if (i == EditorInfo.IME_ACTION_DONE) {
                val (phrase, color) = benderObj.listenAnswer(
                    et_message.text.toString()
                )
                et_message.setText("")
                val (r, g, b) = color
                iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
                tv_text.text = phrase

                hideKeyboard()
                true
            } else false
        }
    }

    /**
     * Если Activity возвращается в приоритетный режим после вызова onStop(),
     * то в этом случае вызывается метод onRestart().
     * Т.е. вызывается после того, как Activity была остановлена и снова запущена пользователем.
     * Всегда сопровождается вызовом метода onStart().
     *
     * используется для специальных действий, которые должны выполняться только при повторном запуске Activity.
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart()")
    }

    /**
     * При вызове onStart() окно ещё не видно пользователю, но вскоре будет видно.
     * Вызывается непосредственно перед тем, как активность станет видимой пользователю.
     *
     * Чтение из БД
     * Запуск сложной анимации
     * Запуск потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Затем следует onResume(), если Activity выходит на передний план.
     */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart()")
    }

    /**
     * Вызывается, когда Activity начинает взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио и видео
     * регистрация любых BroadcastReceiver или других процессов, которые вы освободили/приостановили в onPause()
     * выполнение любых других инициализаций, которые должны происходить, когда Activity вновь активна (камера).
     *
     * Тут должен быть максимально лёгкий и быстрый код, чтобы приложение оставалось отзывчивым.
     */
    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume()")
    }

    /**
     * Метод onPause() вызывается после сворачивания текущей активности или перехода к новой.
     * От onPause() можно перейти к вызову либо onResume(), либо onStop().
     *
     * остановка анимации, аудио и видео
     * сохранение состояния пользовательского ввода (лёгкие процессы)
     * сохранение в БД, если данные должны быть доступны в новой Activity
     * остановка сервисов, подписок, BroadcastReceiver
     *
     * Тут должен быть максимально лёгкий и быстрый код, чтобы приложение оставалось отзывчивым.
     */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause()")
    }

    /**
     * Метод onStop() вызывается, когда Activity становится невидимой пользователю.
     * Это может произойти при её уничтожении, или если была запущена другая Activity (существующая или новая),
     * перекрывающая окно текущей Activity.
     *
     * запись в БД
     * приостановка сложной анимации
     * приостановка потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при вызове метода finish() у Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop()")
    }

    /**
     * Метод вызывается по окончании работы Activity, при вызове метода finish() или в случае,
     * когда система уничтожает этот экземпляр активности для освобождения ресурсов.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val et_message = findViewById<EditText>(R.id.et_message)

        val message = et_message.text.toString()

        outState.putString("STATUS", benderObj.status.name)
        outState.putString("QUESTION", benderObj.question.name)
        outState.putString("ANSWER", message)
        Log.d("M_MainActivity","onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name} $message")
    }

    override fun onClick(p0: View?) {
        val iv_bender = findViewById<ImageView>(R.id.iv_bender)
        val tv_text = findViewById<TextView>(R.id.tv_text)
        val et_message = findViewById<EditText>(R.id.et_message)

        if (p0?.id == R.id.iv_send) {
            val (phrase, color) = benderObj.listenAnswer(et_message.text.toString())
            et_message.setText("")
            val (r, g, b) = color
            iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            tv_text.text = phrase
        }

        hideKeyboard()
    }
}