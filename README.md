# Internship application task 2 for the *Unlocking the potential of AI-Powered Code Assistance with GPT-3 and Codex* position

## Task prompt
A programmer has an upcoming exam and only has `N` hours left to prepare.
The textbook has `M` topics, each of which requires a certain amount of hours `Ti` for studying,
and has a certain number of questions `Ki` that could potentially appear on the exam.
Unfortunately,`N` hours is not enough time to completely study all the topics, but the exam task will have
`L` questions, chosen randomly from all the questions in the textbook with an equal probability.
What is the best way to use limited time to maximize the chances of getting the best possible grade?

## Implementation idea
I will use a greedy algorithm to determine the best combination of exercises to be completed. Given that in the exam,
all exercises have the same probability of appearing, the best choice would be for the student to first do the
chapters that have the **most questions** but that require the **least amount of time** to be completed. Therefore,
we can choose the chapters in _descending_ order based on the ratio of `Ki / Ti`, with `Ki` the number of questions
topic `i` has, and `Ti` the number of hours it takes to do the `i-th` topic.

In case a chosen chapter is too large to be studied in the remaining time, the algorithm will choose the next 
best option. If there are no chapters that would fit in the remaining time, the algorithm will suggest the best chapter
to be partially studied.

## Input
Please refer to the _input.txt_ file under _src/resources_.
It contains instructions on how the input should be structured.

## Running the application
Please run the `Main.main()` method. This will automatically output the list of chapters to study, 
in a to-do list format.

## Testing
There is 95% Method coverage and 90% Line coverage. The only method that is not tested is 
`Main.main()`, but all the functions called in `main` are tested.

## Complexity
### 1. Time complexity

The time complexity of the `StudyPlanGenerator.createChapters()` method is `O(M log(M))`
because of the `sort` method.

For `StudyPlanGenerator.calculateOptimalPlan()`, the complexity is upper bounded by `M`.

And for the `StudyPlanGenerator.printOptimalPlan()`, the complexity is also `O(M)`, as there can be at most
`M` chapters to be studied.

All other methods of the `StudyPlanGenerator` class run in constant time. The same applies for all methods of the
`Chapter` class.

Therefore, the **total complexity** of the program is `O(M + M + M*log(M) + C) = O(M log(M))`, where `C` is a constant.

### 2. Space complexity
The total **space complexity** is `O(3 * M + C) = O(M)`, for `M` the number of topics, and some constant `C`.
