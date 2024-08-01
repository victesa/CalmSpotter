package com.victorkirui.calmspotter.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AffirmationsViewModel @Inject constructor(): ViewModel() {
    val quotesAndAuthors = listOf(
        "The only way to do great work is to love what you do." to "Steve Jobs",
        "Believe you can and you're halfway there." to "Theodore Roosevelt",
        "The future belongs to those who believe in the beauty of their dreams." to "Eleanor Roosevelt",
        "Success is not final, failure is not fatal: It is the courage to continue that counts." to "Winston Churchill",
        "You are never too old to set another goal or to dream a new dream." to "C.S. Lewis",
        "Happiness is not something ready-made. It comes from your own actions." to "Dalai Lama",
        "The only limit to our realization of tomorrow will be our doubts of today." to "Franklin D. Roosevelt",
        "In the middle of difficulty lies opportunity." to "Albert Einstein",
        "Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle." to "Christian D. Larson",
        "You miss 100% of the shots you don't take." to "Wayne Gretzky",
        "Success is stumbling from failure to failure with no loss of enthusiasm." to "Winston S. Churchill",
        "The biggest adventure you can take is to live the life of your dreams." to "Oprah Winfrey",
        "You are the author of your own story. Make it a bestseller." to "Unknown",
        "The best way to predict the future is to create it." to "Abraham Lincoln",
        "Every accomplishment starts with the decision to try." to "John F. Kennedy",
        "The only person you are destined to become is the person you decide to be." to "Ralph Waldo Emerson",
        "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful." to "Albert Schweitzer",
        "It always seems impossible until it is done." to "Nelson Mandela",
        "Opportunities don't happen, you create them." to "Chris Grosser",
        "Dream big and dare to fail." to "Norman Vaughan",
        "You don't have to see the whole staircase, just take the first step." to "Martin Luther King Jr.",
        "It does not matter how slowly you go as long as you do not stop." to "Confucius",
        "The harder you work for something, the greater you'll feel when you achieve it." to "Unknown",
        "Your time is limited, don't waste it living someone else's life." to "Steve Jobs",
        "Don't watch the clock; do what it does. Keep going." to "Sam Levenson"
    )
}