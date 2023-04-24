# Internship application task 2 for the *Unlocking the potential of AI-Powered Code Assistance with GPT-3 and Codex* position

## Remark
This project is archived and no longer worked on.

## Task prompt
A programmer has an upcoming exam and only has `N` hours left to prepare.
The textbook has `M` topics, each of which requires a certain amount of hours `Ti` for studying,
and has a certain number of questions `Ki` that could potentially appear on the exam.
Unfortunately,`N` hours is not enough time to completely study all the topics, but the exam task will have
`L` questions, chosen randomly from all the questions in the textbook with an equal probability.
What is the best way to use limited time to maximize the chances of getting the best possible grade?

## Implementation idea
I will use a dynamic programming algorithm to determine the best combination of exercises to be completed.
At every step, the algorithm calculates if for the given combination of hours remaining (`N-i`), `Kj` and `Tj` it would
be better to skip the exercise or include it in the study plan.

The formula is `memo[i][j] = Math.max(memo[i][j-1], memo[Math.max(0, i-time[j])][j-1] + questions[j])`, where `i` is the
current amount of hours already studied, `j` is the number of the chapter in question. If the first term of the
`Math.max()` function is bigger, we are better with skipping the current chapter.
If the latter is bigger, that means we would gain more from studying the current chapter. 

To put it in a more abstract way, at a point in time `i`, we "look back" and see
how the path chosen at hour `i - Tj` to study the current chapter, compares to all other paths that would lead us to
the current time `i`, but at the end of the previous chapter (`j-1`). This propagates through the memory matrix and
leads to the result (total number of problems which the students studied) at `memo[N][M]`.

From there, we backtrack, to find the chapters that lead to this optimal solution. To find them, we check if the value
in the memory array at position `[currentHour][j]` is equal to the value from `[currentHour-Tj][j-1]` plus `K[j]`.
If they are equal, it means that the `j-th` chapter is part of the optimal solution.

## Input
Please refer to the _input.txt_ file under _src/resources_.
It contains instructions on how the input should be structured.

## Running the application
Please run the `Main.main()` method. This will automatically output the list of chapters to study, 
in a to-do list format.

## Testing
There is 93% Method and Line coverage. The only method that is not tested is 
`Main.main()`, but all the functions called in `main` are tested.

## Complexity
### 1. Time complexity

The time complexity of the `DPStudyPlanGenerator.calculateOptimalPlan()` method is `O(NM)` for the memoization step and
`O(M)` for the backtracking step which select the chapters to be studied. Therefore, the total complexity is `O(NM)`.

And for the `DPStudyPlanGenerator.printOptimalPlan()`, the complexity is also `O(M)`, as there can be at most
`M` chapters to be studied.

All other methods of the `DPStudyPlanGenerator` class run in constant time.

Therefore, the **total complexity** of the program is `O(M + NM + C) = O(NM)`, where `C` is a constant.

### 2. Space complexity
The total **space complexity** is upper bounded by`O(NM)`, for `N` the number of hours to study
and `M` the number of topics. This is because of the memoization matrix.
