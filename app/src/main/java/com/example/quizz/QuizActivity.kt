package com.example.quizz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questionsList: ArrayList<QuestionModel>
    private var count: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize questions list
        initializeQuestions()

        // Set up click listeners for each option
        binding.option1.setOnClickListener { handleAnswer(binding.option1) }
        binding.option2.setOnClickListener { handleAnswer(binding.option2) }
        binding.option3.setOnClickListener { handleAnswer(binding.option3) }
        binding.option4.setOnClickListener { handleAnswer(binding.option4) }

        // Load the first question
        loadQuestion()
    }

    private fun initializeQuestions() {
        questionsList = arrayListOf(
            QuestionModel("What is used to create an instance of a class in Java?", "new", "class", "instance", "create", "new"),
            QuestionModel("Which symbol is used to denote a preprocessor directive in C?", "#", "//", "/*", "<>", "#"),
            QuestionModel("What is the output of 3 + 2 * 4 in most programming languages?", "11", "20", "14", "10", "11"),
            QuestionModel("Which language is primarily used for iOS development?", "Swift", "Java", "Kotlin", "Python", "Swift"),
            QuestionModel("What does CSS stand for?", "Cascading Style Sheets", "Computer Style Sheets", "Creative Style Sheets", "Colorful Style Sheets", "Cascading Style Sheets"),
            QuestionModel("Which keyword is used to declare a constant in Java?", "final", "const", "static", "constant", "final"),
            QuestionModel("Which of these is a NoSQL database?", "MongoDB", "MySQL", "Oracle", "PostgreSQL", "MongoDB"),
            QuestionModel("What does IDE stand for?", "Integrated Development Environment", "Interactive Development Environment", "Internet Development Environment", "Innovative Design Environment", "Integrated Development Environment"),
            QuestionModel("In Python, what does 'import' do?", "Imports a module", "Defines a function", "Creates a variable", "Starts a loop", "Imports a module"),
            QuestionModel("Which is the most used data type in SQL for storing text?", "VARCHAR", "INT", "TEXT", "STRING", "VARCHAR"),
            QuestionModel("What is a binary tree?", "A tree with each node having at most two children", "A tree with binary values", "A tree with only two nodes", "A tree with two levels", "A tree with each node having at most two children"),
            QuestionModel("Which of these is a frontend framework?", "React", "Flask", "Django", "Spring", "React"),
            QuestionModel("Which command is used to create a directory in Linux?", "mkdir", "rmdir", "ls", "cd", "mkdir"),
            QuestionModel("What does SQL stand for?", "Structured Query Language", "Sequential Query Language", "Standard Query Language", "Sample Query Language", "Structured Query Language"),
            QuestionModel("Which operator is used for exponentiation in Python?", "**", "^", "*", "//", "**"),
            QuestionModel("What does JSON stand for?", "JavaScript Object Notation", "JavaScript Oriented Notation", "Java Sequence Object Notation", "Java System Output Notation", "JavaScript Object Notation"),
            QuestionModel("In Python, which method converts a string to lowercase?", "lower()", "toLowerCase()", "convertToLower()", "small()", "lower()"),
            QuestionModel("What is the time complexity of binary search?", "O(log n)", "O(n)", "O(n^2)", "O(1)", "O(log n)"),
            QuestionModel("Which of these is a backend language?", "Python", "HTML", "CSS", "Bootstrap", "Python"),
            QuestionModel("What is the purpose of a constructor in a class?", "To initialize an object", "To destroy an object", "To compare objects", "To print an object", "To initialize an object"),
            QuestionModel("Which method is used to add an item to an ArrayList in Java?", "add()", "insert()", "append()", "push()", "add()"),
            QuestionModel("What does CSS stand for?", "Cascading Style Sheets", "Computer Style Sheets", "Creative Style Sheets", "Colorful Style Sheets", "Cascading Style Sheets"),
            QuestionModel("What symbol is used to end a statement in C++?", ";", ":", ".", "#", ";"),
            QuestionModel("In JavaScript, what is used to declare a variable?", "var", "int", "String", "declare", "var"),
            QuestionModel("Which of these is a loop structure?", "for", "if", "switch", "case", "for"),
            QuestionModel("In Python, what does the 'append' method do?", "Adds an item to the end of a list", "Removes an item from a list", "Sorts a list", "Clears a list", "Adds an item to the end of a list"),
            QuestionModel("Which of these is a relational database?", "MySQL", "MongoDB", "Cassandra", "Redis", "MySQL"),
            QuestionModel("What is the keyword for inheritance in Python?", "class", "def", "super", "inherit", "class"),
            QuestionModel("In C++, what is the size of a float?", "4 bytes", "2 bytes", "8 bytes", "1 byte", "4 bytes"),
            QuestionModel("What symbol is used to start a comment in HTML?", "<!--", "//", "#", "<>", "<!--"),
            QuestionModel("What is the size of int in C?", "4 bytes", "2 bytes", "8 bytes", "1 byte", "4 bytes"),
            QuestionModel("Which HTML tag is used for the largest heading?", "<h1>", "<head>", "<h6>", "<title>", "<h1>"),
            QuestionModel("In C++, which operator is used for bitwise OR?", "|", "||", "&", "!", "|"),
            QuestionModel("What is a 'char' in programming?", "A data type for characters", "A type of loop", "A boolean type", "A float", "A data type for characters"),
            QuestionModel("What is the full form of XML?", "Extensible Markup Language", "Extra Markup Language", "Extensive Markup Language", "Example Markup Language", "Extensible Markup Language"),
            QuestionModel("Which Python function is used to create an empty dictionary?", "dict()", "list()", "map()", "tuple()", "dict()"),
            QuestionModel("Which method is used to check the size of an ArrayList in Java?", "size()", "length()", "getSize()", "count()", "size()"),
            QuestionModel("In SQL, which command is used to update data?", "UPDATE", "SELECT", "DELETE", "INSERT", "UPDATE"),
            QuestionModel("What is encapsulation in OOP?", "Restricting access to certain components", "Reusing code", "Defining a class", "Writing functions", "Restricting access to certain components"),
            QuestionModel("Which of these is a functional programming language?", "Haskell", "Java", "C", "C++", "Haskell"),
            QuestionModel("Which HTML tag is used to create a hyperlink?", "<a>", "<link>", "<url>", "<href>", "<a>"),
            QuestionModel("What is the purpose of Git?", "Version control", "Database management", "Network configuration", "Operating system", "Version control"),
            QuestionModel("Which method in Python removes the last item in a list?", "pop()", "remove()", "delete()", "drop()", "pop()"),
            QuestionModel("In SQL, what is a foreign key?", "A field that links to a primary key in another table", "A unique identifier", "A primary key", "A command to delete data", "A field that links to a primary key in another table"),
            QuestionModel("What does HTTP stand for?", "HyperText Transfer Protocol", "HyperTool Transfer Protocol", "HighText Transfer Protocol", "HyperText Transfer Program", "HyperText Transfer Protocol"),
            QuestionModel("In Java, which method is used to read input from the user?", "Scanner.nextLine()", "input()", "read()", "System.in", "Scanner.nextLine()"),
            QuestionModel("What is polymorphism?", "Ability of a function to behave differently based on input", "Ability to store many items", "Ability to inherit properties", "Ability to loop through items", "Ability of a function to behave differently based on input"),
            QuestionModel("What is the purpose of a firewall?", "To protect a network", "To accelerate software", "To display graphics", "To compile code", "To protect a network"),
            QuestionModel("Which command is used to view contents of a file in Linux?", "cat", "mkdir", "ls", "pwd", "cat"),
            QuestionModel("What is inheritance in OOP?", "One class inheriting properties of another", "A function calling itself", "Using a data structure", "An operator overloading", "One class inheriting properties of another"),
            QuestionModel("Which Python library is popular for data analysis?", "Pandas", "NumPy", "React", "TensorFlow", "Pandas"),
            QuestionModel("What does JSON.parse() do in JavaScript?", "Converts JSON string to JavaScript object", "Converts JSON object to string", "Writes data to JSON", "Clears JSON data", "Converts JSON string to JavaScript object")
        )

        // Add more questions as needed

    }

    private fun loadQuestion() {
        if (count < questionsList.size) {
            val currentQuestion = questionsList[count]
            binding.question.text = currentQuestion.question
            binding.option1.text = currentQuestion.option1
            binding.option2.text = currentQuestion.option2
            binding.option3.text = currentQuestion.option3
            binding.option4.text = currentQuestion.option4

            // Reset background colors and re-enable buttons
            resetOptions()
        } else {
            showScore()
        }
    }

    private fun handleAnswer(selectedOption: android.view.View) {
        // Disable all option buttons
        binding.option1.isEnabled = false
        binding.option2.isEnabled = false
        binding.option3.isEnabled = false
        binding.option4.isEnabled = false

        // Get the correct answer
        val correctAnswer = questionsList[count].ans

        // Check if selected answer is correct
        if ((selectedOption as? android.widget.Button)?.text.toString() == correctAnswer) {
            score++
            selectedOption.setBackgroundColor(Color.GREEN) // Highlight correct answer in green
        } else {
            selectedOption.setBackgroundColor(Color.RED) // Highlight wrong answer in red
            // Highlight the correct answer
            when (correctAnswer) {
                binding.option1.text -> binding.option1.setBackgroundColor(Color.GREEN)
                binding.option2.text -> binding.option2.setBackgroundColor(Color.GREEN)
                binding.option3.text -> binding.option3.setBackgroundColor(Color.GREEN)
                binding.option4.text -> binding.option4.setBackgroundColor(Color.GREEN)
            }
        }

        // Delay for a moment before loading the next question
        Handler(Looper.getMainLooper()).postDelayed({
            count++
            loadQuestion()
        }, 1000) // 1000 milliseconds delay
    }

    private fun resetOptions() {
        binding.option1.setBackgroundColor(Color.WHITE)
        binding.option2.setBackgroundColor(Color.WHITE)
        binding.option3.setBackgroundColor(Color.WHITE)
        binding.option4.setBackgroundColor(Color.WHITE)
        binding.option1.isEnabled = true
        binding.option2.isEnabled = true
        binding.option3.isEnabled = true
        binding.option4.isEnabled = true
    }

    private fun showScore() {
        val intent = Intent(this, ScoreActivity::class.java).apply {
            putExtra("Score", score)
        }
        startActivity(intent)
        finish()
    }

    companion object {
        const val SCORE_KEY = "Score"
    }
}
