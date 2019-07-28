package com.example.quotesapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuoteLibrary {

    public static List<Quote> createLibrary () {

        List quoteMasterArray = new ArrayList<Quote>();
/*
        quoteMasterArray.add("'quote one', 'author one', 'categoryOne, categoryTwo', 0");
        quoteMasterArray.add("'quote two', 'author two', 'categoryOne, categoryThree', 0");
        quoteMasterArray.add("'quote three', 'author three', 'categoryTwo, categoryThree, categoryFour', 0");
        quoteMasterArray.add("'quote four', 'author one', 'categoryOne, categoryFour', 0");
        quoteMasterArray.add("'quote five', 'author four', 'categoryOne, categoryFour', 0");
        quoteMasterArray.add("'quote six', 'author four', 'categoryOne', 0");
        quoteMasterArray.add("'quote seven', 'author five', 'categoryFive', 0");
        quoteMasterArray.add("'quote eight is a really long quote to help test how well it fits into the display on the app as it could get quite excessive if it goes on too long', 'author six sir reginald de waltzy hammersmith who has too many titles for his own good', 'categoryFive', 0");
        quoteMasterArray.add("'quote nine tests the database rebuild feature', 'author six', 'categorySix, categoryOne, categoryFour', 0");
        quoteMasterArray.add("'quote ten tests the database update feature', 'author seven', 'categorySix, categoryOne, categoryFour, categoryThree', 0");

//        quoteMasterArray.add("'', '', '', 0");

        quoteMasterArray.add("'Quality is not an act, it is a habit.', 'Aristotle', 'Self Improvement, Perception', 0");
        quoteMasterArray.add("'Well done is better than well said.', 'Benjamin Franklin', 'Action, Motivation', 0");
        quoteMasterArray.add("'By failing to prepare, you are preparing to fail.', 'Benjamin  Franklin', 'Action, Responsibility', 0");
        quoteMasterArray.add("'It does not matter how slowly you go as long as you do not stop.', 'Confucius', 'Motivation, Action, Self Improvement', 0");
        quoteMasterArray.add("'The more man meditates upon good thoughts, the better will be his world and the world at large.', 'Confucius', 'Perception, Optimism', 0");
        quoteMasterArray.add("'The will to win, the desire to succeed, the urge to reach your full potential... These are the keys that will unlock the door to personal excellence.', 'Confucius', 'Motivation, Action, Self Improvement', 0");
        quoteMasterArray.add("'Be kind whenever possible. It is always possible.', 'Dalai Lama', 'Kindness, Optimism', 0");
        quoteMasterArray.add("'You can''t build a reputation on what you are going to do.', 'Henry Ford', 'Action, Purpose, Self Reliance', 0");
        quoteMasterArray.add("'Whether you believe you can, or believe you can''t, you are right.', 'Henry Ford', 'Perception', 0");
        quoteMasterArray.add("'If you''re going through Hell, keep going.', 'Winston Churchill', 'Action, Persistence', 0");

//        quoteMasterArray.add(new Quote("", "", new ArrayList<>(Arrays.asList()), 0));
*/

        quoteMasterArray.add(new Quote("Infuse your life with action. Don''t wait for it to happen. Make it happen. Make your own future. Make your own hope. Make your own love. And whatever your beliefs, honor your creator, not by passively waiting for grace to come down from upon high, but by doing what you can to make grace happen... yourself, right now, right down here on Earth.", "Bradley Whitford", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Reliance")), 0));
        quoteMasterArray.add(new Quote("Optimism is the faith that leads to achievement. Nothing can be done without hope and confidence.", "Helen Keller", new ArrayList<>(Arrays.asList("Motivation", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Life is 10% what happens to you and 90% how you react to it.", "Charles R. Swindoll", new ArrayList<>(Arrays.asList("Motivation", "Perception")), 0));
        quoteMasterArray.add(new Quote("Good, better, best. Never let it rest. ''Til your good is better and your better is best.", "St. Jerome", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("The past cannot be changed. The future is yet in your power.", "Unknown", new ArrayList<>(Arrays.asList("Motivation", "Optimism")), 0));
        quoteMasterArray.add(new Quote("It does not matter how slowly you go as long as you do not stop.", "Confucius", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Only I can change my life. No one can do it for me.", "Carol Burnett", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Change your life today. Don''t gamble on the future, act now, without delay.", "Simon de Beauvoir", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Failure will never overtake me if my determination to succeed is strong enough.", "Og Mandino", new ArrayList<>(Arrays.asList("Motivation", "Determination")), 0));
        quoteMasterArray.add(new Quote("Believe in yourself! Have faith in your abilities! Without a humble but reasonable confidence in your own powers you cannot be successful or happy.", "Norman Vincent Peale", new ArrayList<>(Arrays.asList("Motivation", "Self Reliance", "Optimism")), 0));
        quoteMasterArray.add(new Quote("With the new day comes new strength and new thoughts.", "Eleanor Roosevelt", new ArrayList<>(Arrays.asList("Motivation", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Well done is better than well said.", "Benjamin Franklin", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("Do not wait; the time will never be ''just right.'' Start where you stand, and work with whatever tools you may have at your command, and better tools will be found as you go along.", "George Herbert", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Start where you are. Use what you have. Do what you can.", "Arthur Ashe", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("The will to win, the desire to succeed, the urge to reach your full potential... These are the keys that will unlock the door to personal excellence.", "Confucius", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("The secret of getting ahead is getting started.", "Mark Twain", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("Beginning today, treat everyone you meet as if they were going to be dead by midnight. Extend to them all the care, kindness and understanding you can muster, and do it with no thought of any reward. Your life will never be the same again.", "Og Mandino", new ArrayList<>(Arrays.asList("Motivation", "Kindness", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Keep your eyes on the stars, and your feet on the ground.", "Theodore Roosevelt", new ArrayList<>(Arrays.asList("Motivation", "Optimism", "Curiosity")), 0));
        quoteMasterArray.add(new Quote("Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.", "Thomas A. Edison", new ArrayList<>(Arrays.asList("Motivation", "Persistence", "Determination")), 0));
        quoteMasterArray.add(new Quote("We should not give up and we should not allow the problem to defeat us.", "A. P. J. Abdul Kalam", new ArrayList<>(Arrays.asList("Motivation", "Persistence", "Determination")), 0));
        quoteMasterArray.add(new Quote("Look up at the stars and not down at your feet. Try to make sense of what you see, and wonder about what makes the universe exist. Be curious.", "Stephen Hawking", new ArrayList<>(Arrays.asList("Motivation", "Optimism", "Curiosity")), 0));
        quoteMasterArray.add(new Quote("Consult not your fears but your hopes and your dreams. Think not about your frustrations, but about your unfulfilled potential. Concern yourself not with what you tried and failed in, but with what it is still possible for you to do.", "Pope John XXIII", new ArrayList<>(Arrays.asList("Motivation", "Optimism", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Do the difficult things while they are easy and do the great things while they are small. A journey of a thousand miles must begin with a single step.", "Lao Tzu", new ArrayList<>(Arrays.asList("Motivation", "Action", "Determination")), 0));
        quoteMasterArray.add(new Quote("Set your goals high, and don''t stop till you get there.", "Bo Jackson", new ArrayList<>(Arrays.asList("Motivation", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("What you get by achieving your goals is not as important as what you become by achieving your goals.", "Zig Ziglar", new ArrayList<>(Arrays.asList("Motivation")), 0));
        quoteMasterArray.add(new Quote("Don''t feel bad for making decisions that upset other people. You''re not responsible for their happiness, you''re responsible for yours.", "Unknown", new ArrayList<>(Arrays.asList("Self Care")), 0));
        quoteMasterArray.add(new Quote("Keep this thought handy when you feel a fit of rage coming on—it isn’t manly to be enraged. Rather, gentleness and civility are more human, and therefore manlier. A real man doesn’t give way to anger and discontent, and such a person has strength, courage, and endurance—unlike the angry and complaining. The nearer a man comes to a calm mind, the closer he is to strength.", "Marcus Aurelius", new ArrayList<>(Arrays.asList("Self Control")), 0));
        quoteMasterArray.add(new Quote("The inferior man argues about his rights, while the superior man imposes duties on himself.", "Wagner Clemente Soto", new ArrayList<>(Arrays.asList("Responsibility")), 0));
        quoteMasterArray.add(new Quote("A dream written down with a date becomes a goal. A goal broken down into steps becomes a plan. A plan backed by action becomes reality.", "Unknown", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("It is the job of concious rational thought to decide what you want, select the goals you wish to achieve - and concentrate upon these rather than upon what you do not want. To spend time and effort concentrating upon what you do not want is not rational.", "Maxwell Maltz - Psycho-Cybernetics", new ArrayList<>(Arrays.asList("Ambition")), 0));
        quoteMasterArray.add(new Quote("The hardest walk you can make is the walk you make alone. But that is the walk that makes you the strongest.", "Unknown", new ArrayList<>(Arrays.asList("Determination")), 0));
        quoteMasterArray.add(new Quote("If size mattered, the elephant would be the King of the jungle.", "Rickson Gracie", new ArrayList<>(Arrays.asList("Perception")), 0));
        quoteMasterArray.add(new Quote("You are not here merely to make a living. You are here in order to enable the world to live more amply, with greater vision, with a finer spirit of hope and achievement. You are here to enrich the world, and you impoverish yourself if you forget the errand.", "Woodrow Wilson", new ArrayList<>(Arrays.asList("Purpose")), 0));
        quoteMasterArray.add(new Quote("What you do today can improve all your tomorrows.", "Ralph Marston", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("Your talent is God''s gift to you. What you do with it is your gift back to God.", "Leo Buscaglia", new ArrayList<>(Arrays.asList("Motivation", "Action")), 0));
        quoteMasterArray.add(new Quote("Motivation is the art of getting people to do what you want them to do because they want to do it.", "Dwight D. Eisenhower", new ArrayList<>(Arrays.asList("Motivation", "Influence")), 0));
        quoteMasterArray.add(new Quote("You can''t cross the sea merely by standing and staring at the water.", "Rabindranath Tagore", new ArrayList<>(Arrays.asList("Action")), 0));
        quoteMasterArray.add(new Quote("Do the thing you think you cannot do. Fail at it. Try again. Do better the second time. The only people who never tumble are those who never mount the high wire. This is your moment. Own it.", "Oprah Winfrey", new ArrayList<>(Arrays.asList("Action", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Do your work with your whole heart, and you will succeed - there''s so little competition.", "Elbert Hubbard", new ArrayList<>(Arrays.asList("Determination", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Problems are not stop signs, they are guidelines.", "Robert H. Schuller", new ArrayList<>(Arrays.asList("Determination", "Optimism", "Perception")), 0));
        quoteMasterArray.add(new Quote("Learning is the beginning of wealth. Learning is the beginning of health. Learning is the beginningof spirituality. Searching and learning is where the miracle process all beings.", "Jim Rohn", new ArrayList<>(Arrays.asList("Self Improvement", "Curiosity", "Spirituality")), 0));
        quoteMasterArray.add(new Quote("You have to learn the rules of the game. And then you have to play better than anyone else.", "Unknown", new ArrayList<>(Arrays.asList("Determination", "Action", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Be Impeccable With Your Word. Speak with integrity. Say only what you mean. Avoid using the word to speak against yourself or gossip about others. Use the power of your word in the direction of truth and love.", "Don Miguel Ruiz", new ArrayList<>(Arrays.asList("Integrity", "Language")), 0));
        quoteMasterArray.add(new Quote("Accept the challenges so that you can feel the exhilaration of victory.", "George S. Patton", new ArrayList<>(Arrays.asList("Perception", "Determination")), 0));
        quoteMasterArray.add(new Quote("When something is important enough, you do it even if the odds are not in your favour.", "Elon Musk", new ArrayList<>(Arrays.asList("Determination", "Motivation")), 0));
        quoteMasterArray.add(new Quote("When you reach the end of your rope, tie a knot in it and hang on.", "Franklin D. Roosevelt", new ArrayList<>(Arrays.asList("Determination", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Follow your dreams, work hard, practice and persevere. Make sure you eat a variety of foods, get plenty of exercise and maintain a healthy lifestyle.", "Sasha Cohen", new ArrayList<>(Arrays.asList("Self Care", "Self Reliance", "Determination")), 0));
        quoteMasterArray.add(new Quote("It always seems impossible until it''s done.", "Nelson Mandela", new ArrayList<>(Arrays.asList("Determination", "Persistence", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Do you want to know who you are? Don''t ask. Act! Action will delineate and define you.", "Thomas Jefferson", new ArrayList<>(Arrays.asList("Purpose", "Action")), 0));
        quoteMasterArray.add(new Quote("I think people who are creative are the luckiest people on earth. I know that there are no shortcuts, but you must keep your faith in something Greater than You, and keep doing what you love. Do what you love, and you will find the way to get it out to the world.", "Judy Collins", new ArrayList<>(Arrays.asList("Faith", "Creativity", "Persistence")), 0));
        quoteMasterArray.add(new Quote("There is no passion to be found playing small - in settling for a life that is less than the one you are capable of living.", "Nelson Mandela", new ArrayList<>(Arrays.asList("Ambition")), 0));
        quoteMasterArray.add(new Quote("We are taught you must blame your father, your sisters, your brothers, the school, the teachers - but never blame yourself. It''s never your fault. But it''s always your fault, because if you wanted to change you''re the one who has got to change.", "Katharine Hepburn", new ArrayList<>(Arrays.asList("Ownership", "Self Reliance", "Responsibility")), 0));
        quoteMasterArray.add(new Quote("I don''t believe you have to be better than everybody else. I believe you have to be better than you ever thought you could be.", "Ken Venturi", new ArrayList<>(Arrays.asList("Self Improvement", "Ambition", "Perception")), 0));
        quoteMasterArray.add(new Quote("Never give up, for that is just the place and time that the tide will turn.", "Harriet Beecher Stowe", new ArrayList<>(Arrays.asList("Persistence")), 0));
        quoteMasterArray.add(new Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others.", "Ayn Rand", new ArrayList<>(Arrays.asList("Creativity", "Ambition", "Perception")), 0));
        quoteMasterArray.add(new Quote("I''d rather attempt to do something great and fail than to attempt to do nothing and succeed.", "Robert H. Schuller", new ArrayList<>(Arrays.asList("Determination", "Ambition")), 0));
        quoteMasterArray.add(new Quote("Poverty was the greatest motivating factor in my life.", "Jimmy Dean", new ArrayList<>(Arrays.asList("Motivation", "Ambition")), 0));
        quoteMasterArray.add(new Quote("Quality is not an act, it is a habit.", "Aristotle", new ArrayList<>(Arrays.asList("Self Improvement", "Perception")), 0));
        quoteMasterArray.add(new Quote("Setting goals is the first step in turning the invisible into the visible.", "Tony Robbins", new ArrayList<>(Arrays.asList("Action", "Motivation", "Purpose")), 0));
        quoteMasterArray.add(new Quote("The more man meditates upon good thoughts, the better will be his world and the world at large.", "Confucius", new ArrayList<>(Arrays.asList("Perception", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Things do not happen. Things are made to happen.", "John F. Kennedy", new ArrayList<>(Arrays.asList("Action", "Motivation", "Determination")), 0));
        quoteMasterArray.add(new Quote("Without hard work, nothing grows but weeds.", "Gordon B. Hinckley", new ArrayList<>(Arrays.asList("Action", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Perseverance is not a long race; it is many short races one after the other.", "Walter Elliot", new ArrayList<>(Arrays.asList("Persistence", "Determination")), 0));
        quoteMasterArray.add(new Quote("Don''t watch the clock; do what it does. Keep going.", "Sam Levenson", new ArrayList<>(Arrays.asList("Persistence", "Determination", "Action")), 0));
        quoteMasterArray.add(new Quote("I know where I''m going and I know the truth, and I don''t have to be what you want me to be. I''m free to be what I want.", "Muhammad Ali", new ArrayList<>(Arrays.asList("Motivation", "Self Reliance", "Ambition")), 0));
        quoteMasterArray.add(new Quote("The key is to keep company only with people who uplift you, whose presence calls forth your best.", "Epictetus", new ArrayList<>(Arrays.asList("Integrity", "Self Improvement", "Self Care")), 0));
        quoteMasterArray.add(new Quote("Learn from the past, set vivid, detailed goals for the future, and live in the only moment of time over which you have any control: now.", "Denis Waitley", new ArrayList<>(Arrays.asList("Self Improvement", "Ambition", "Action")), 0));
        quoteMasterArray.add(new Quote("Aim for the moon. If you miss, you may hit a star.", "W. Clement Stone", new ArrayList<>(Arrays.asList("Ambition", "Optimism")), 0));
        quoteMasterArray.add(new Quote("If you want to succeed you should strike out on new paths, rather than travel the worn paths of accepted success.", "John D. Rockerfeller", new ArrayList<>(Arrays.asList("Ambition", "Determination")), 0));
        quoteMasterArray.add(new Quote("Be miserable. Or motivate yourself. Whatever has to be done, it''s always your choice.", "Wayne Dyer", new ArrayList<>(Arrays.asList("Responsibility", "Optimism", "Motivation")), 0));
        quoteMasterArray.add(new Quote("If you''re going through hell, keep going.", "Winston Churchill", new ArrayList<>(Arrays.asList("Action", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Where there is a will, there is a way. If there is a chance in a million that you can do something, anything, to keep what you want from ending, do it. Pry the door open or, if need be, wedge your foot in the door and keep it open.", "Pauline Kael", new ArrayList<>(Arrays.asList("Determination", "Persistence", "Optimism")), 0));
        quoteMasterArray.add(new Quote("You are never too old to set another goal or to dream a new dream.", "Les Brown", new ArrayList<>(Arrays.asList("Ambition", "Optimism", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("If you fell down yesterday, stand up today.", "H. G. Wells", new ArrayList<>(Arrays.asList("Determination", "Self Improvement", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Knowing is not enough; we must apply. Willing is not enough; we must do.", "Johann Wolfgang von Goethe", new ArrayList<>(Arrays.asList("Action")), 0));
        quoteMasterArray.add(new Quote("By failing to prepare, you are preparing to fail.", "Benjamin Franklin", new ArrayList<>(Arrays.asList("Action", "Responsibility")), 0));
        quoteMasterArray.add(new Quote("In order to succeed, we must first believe that we can.", "Nikos Kazantzakis", new ArrayList<>(Arrays.asList("Determination", "Ambition", "Self Reliance")), 0));
        quoteMasterArray.add(new Quote("Do not wait to strike till the iron is hot; but make it hot by striking.", "William Butler Yeats", new ArrayList<>(Arrays.asList("Action")), 0));
        quoteMasterArray.add(new Quote("There is only one corner of the universe you can be certain of improving, and that''s your own self.", "Aldous Huxley", new ArrayList<>(Arrays.asList("Self Improvement")), 0));
        quoteMasterArray.add(new Quote("No matter how many goals you have achieved, you must set your sights on a higher one.", "Jessica Savitch", new ArrayList<>(Arrays.asList("Ambition")), 0));
        quoteMasterArray.add(new Quote("I want to appeal to people, and therefore, I talk from the heart.", "Sangram Singh", new ArrayList<>(Arrays.asList("Kindness", "Language", "Motivation")), 0));
        quoteMasterArray.add(new Quote("If you want to conquer fear, don''t sit home and think about it. Go out and get busy.", "Dale Carnegie", new ArrayList<>(Arrays.asList("Action", "Self Improvement", "Ambition")), 0));
        quoteMasterArray.add(new Quote("We may encounter many defeats but we must not be defeated.", "Maya Angelou", new ArrayList<>(Arrays.asList("Persistence", "Optimism", "Determination")), 0));
        quoteMasterArray.add(new Quote("Set your sights high, the higher the better. Expect the most wonderful things to happen, not in the future but right now. Realize that nothing is too good. Allow absolutely nothing to hamper you or hold you up in any way.", "Eileen Caddy", new ArrayList<>(Arrays.asList("Ambition", "Optimism", "Determination")), 0));
        quoteMasterArray.add(new Quote("A good plan violently executed now is better than a perfect plan executed next week.", "George S. Patton", new ArrayList<>(Arrays.asList("Action", "Ambition", "Initiative")), 0));
        quoteMasterArray.add(new Quote("I learned that we can do anything, but we can''t do everything... at least not at the same time. So think of your priorities not in terms of what activities you do, but when you do them. Timing is everything.", "Dan Millman", new ArrayList<>(Arrays.asList("Planning")), 0));
        quoteMasterArray.add(new Quote("You can''t build a reputation on what you are going to do.", "Henry Ford", new ArrayList<>(Arrays.asList("Action", "Purpose", "Self Reliance")), 0));
        quoteMasterArray.add(new Quote("The ultimate aim of the ego is not to see something, but to be something.", "Muhammad Iqbal", new ArrayList<>(Arrays.asList("Purpose", "Ambition")), 0));
        quoteMasterArray.add(new Quote("The will to succeed is important, but what''s more important is the will to prepare.", "Bobby Knight", new ArrayList<>(Arrays.asList("Planning", "Ambition", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("Ever tried. Ever failed. No matter. Try again. Fail again. Fail better.", "Samuel Beckett", new ArrayList<>(Arrays.asList("Determination", "Persistence", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("I really believe that everything has a talent, ability, or skill that he can mine to support himself and to succeed in life.", "Dean Koontz", new ArrayList<>(Arrays.asList("Purpose", "Self Reliance", "Ambition")), 0));
        quoteMasterArray.add(new Quote("The way to get started is to quit talking and begin doing.", "Walt Disney", new ArrayList<>(Arrays.asList("Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("Follow your inner moonlight; don''t hide the madness.", "Allen Ginsberg", new ArrayList<>(Arrays.asList("Creativity", "Integrity", "Purpose")), 0));
        quoteMasterArray.add(new Quote("The harder the conflict, the more glorious the triumph.", "Thomas Paine", new ArrayList<>(Arrays.asList("Ambition", "Determination")), 0));
        quoteMasterArray.add(new Quote("Be kind whenever possible. It is always possible.", "Dalai Lama", new ArrayList<>(Arrays.asList("Kindness", "Optimism")), 0));
        quoteMasterArray.add(new Quote("Opportunity does not knock, it presents itself when you beat down the door.", "Kyle Chandler", new ArrayList<>(Arrays.asList("Initiative", "Action")), 0));
        quoteMasterArray.add(new Quote("March on. Do not tarry. To go forward is to move toward perfection. March on, and fear not the thorns, or the sharp stones on life''s path.", "Khalil Gibran", new ArrayList<>(Arrays.asList("Action", "Self Improvement", "Persistence")), 0));
        quoteMasterArray.add(new Quote("You simply have to put one foot in front of the other and keep going. Put blinders on and plow right ahead.", "George Lucas", new ArrayList<>(Arrays.asList("Determination", "Persistence")), 0));
        quoteMasterArray.add(new Quote("I''ve found that luck is quite predictable. If you want more luck, take more chances. Be more active. Show up more often.", "Brian Tracy", new ArrayList<>(Arrays.asList("Persistence", "Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("Either I will find a way or I will make one.", "Philip Sidney", new ArrayList<>(Arrays.asList("Optimism", "Ambition", "Determination")), 0));
        quoteMasterArray.add(new Quote("The first step toward success is taken when you refuse to be a captive of the environment in which you first find yourself.", "Mark Caine", new ArrayList<>(Arrays.asList("Determination", "Ambition", "Action")), 0));
        quoteMasterArray.add(new Quote("You need to overcome the tug of people against you as you reach for high goals.", "George S. Patton", new ArrayList<>(Arrays.asList("Ambition", "Determination")), 0));
        quoteMasterArray.add(new Quote("Determine never to be idle. No person will have occasion to complain of the want of time who never loses any. It is wonderful how much may be done if we are always doing.", "Thomas Jefferson", new ArrayList<>(Arrays.asList("Action")), 0));
        quoteMasterArray.add(new Quote("If you don''t design your own life plan, chances are you''ll fall into someone else''s plan. And guess what they have planned for you? Not much.", "Jim Rohn", new ArrayList<>(Arrays.asList("Ambition", "Action", "Self Reliance")), 0));
        quoteMasterArray.add(new Quote("Either you run the day or the day runs you.", "Jim Rohn", new ArrayList<>(Arrays.asList("Initiative", "Determination", "Action")), 0));
        quoteMasterArray.add(new Quote("Every exit is an entry somewhere else.", "Tom Stoppard", new ArrayList<>(Arrays.asList("Optimism", "Faith", "Perception")), 0));
        quoteMasterArray.add(new Quote("Perseverance is failing 19 times and succeeding the 20th.", "Julie Andrews", new ArrayList<>(Arrays.asList("Persistence", "Ambition")), 0));
        quoteMasterArray.add(new Quote("Arriving at one goal is the starting point to another.", "John Dewey", new ArrayList<>(Arrays.asList("Ambition", "Persistence", "Self Improvement")), 0));
        quoteMasterArray.add(new Quote("You can''t expect to hit the jackpot if you don''t put a few nickels in the machine.", "Flip Wilson", new ArrayList<>(Arrays.asList("Ambition", "Initiative", "Action")), 0));
        quoteMasterArray.add(new Quote("Get action. Sieze the moment. Man was never intended to become an oyster.", "Theodore Roosevelt", new ArrayList<>(Arrays.asList("Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("Whatever you want in life, other people are going to want it too. Believe in yourself enough to accept the idea that you have an equal right to it.", "Diane Sawyer", new ArrayList<>(Arrays.asList("Self Belief", "Determination", "Ambition")), 0));
        quoteMasterArray.add(new Quote("You must take action now that will move you towards your goals. Develop a sense of urgency in your life.", "H. Jackson Brown, Jr", new ArrayList<>(Arrays.asList("Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("Go for it now. The future is promised to no one.", "Wayne Dyer", new ArrayList<>(Arrays.asList("Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("No bird soars too high if he soars with his own wings.", "William Blake", new ArrayList<>(Arrays.asList("Self Reliance")), 0));
        quoteMasterArray.add(new Quote("Go big or go home. Because it''s true. What do you have to lose?", "Eliza Dushku", new ArrayList<>(Arrays.asList("Ambition", "Action")), 0));
        quoteMasterArray.add(new Quote("Press forward. Do not stop, do not linger in your journey, but strive for the mark set before you.", "George Whitefield", new ArrayList<>(Arrays.asList("Persistence", "Determination", "Action")), 0));
        quoteMasterArray.add(new Quote("Big shots are only little shots who keep shooting.", "Christopher Morley", new ArrayList<>(Arrays.asList("Persistence", "Determination", "Action")), 0));
        quoteMasterArray.add(new Quote("The people who influence you are the people who believe in you.", "Henry Drummond", new ArrayList<>(Arrays.asList("Purpose")), 0));
        quoteMasterArray.add(new Quote("Never complain and never explain.", "Benjamin Disraeli", new ArrayList<>(Arrays.asList("Determination")), 0));
        quoteMasterArray.add(new Quote("The hardships that I encountered in the past will help me succeed in the future.", "Philip Emeagwali", new ArrayList<>(Arrays.asList("Self Reliance", "Self Improvement", "Determination")), 0));
        quoteMasterArray.add(new Quote("If you don''t like how things are, change it! You''re not a tree.", "Jim Rohn", new ArrayList<>(Arrays.asList("Self Reliance", "Action", "Determination")), 0));
        quoteMasterArray.add(new Quote("Always continue the climb. It is possible for you to do whatever you choose, if you first get to know who you are and are willing to work with a power that is greater than ourselves to do it.", "Ella Wheeler Wilcox", new ArrayList<>(Arrays.asList("Determination", "Spirituality", "Purpose")), 0));
        quoteMasterArray.add(new Quote("The most effective way to do it, is to do it.", "Amelia Earhart", new ArrayList<>(Arrays.asList("Action", "Initiative")), 0));
        quoteMasterArray.add(new Quote("Never, never, never give up.", "Winston Churchill", new ArrayList<>(Arrays.asList("Determination", "Persistence")), 0));
        quoteMasterArray.add(new Quote("You can never quit. Winners never quit, and quitters never win.", "Ted Turner", new ArrayList<>(Arrays.asList("Determination", "Persistence")), 0));
        quoteMasterArray.add(new Quote("Decide what you want, decide what you are willing to exchange for it. Establish your priorities and go to work.", "H. L. Hunt", new ArrayList<>(Arrays.asList("Ambition", "Self Reliance", "Action")), 0));
        quoteMasterArray.add(new Quote("Whether you believe you can, or believe you can''t, you are right.", "Henry Ford", new ArrayList<>(Arrays.asList("Perception")), 0));


        return quoteMasterArray;
    }
}
