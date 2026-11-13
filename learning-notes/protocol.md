# Exercise protocol

[Home](README.md) · [Roadmap](learning-plan.md) · [Progress](progress.md)

Every exercise follows the same routine, whichever assistant (Codex or
Claude) is helping. The goal is that each concept is explained before you
need it, each attempt is checked by actually running it, and every miss is
recorded where you can revise from it.

## Who does what

| You | The assistant |
|---|---|
| Write all exercise code | Prepares the notes before each exercise |
| Write predictions, attempt, run, and ask for hints | Runs, tests, and reviews your attempt |
| Explain your answers in your own words | Writes the review and updates the notes |
| Run git commands | Gives you the git commands to run |

The assistant never edits exercise source files. Examples in the notes are
separate programs, not solutions to the TODOs.

## Full routine or quick round

Each exercise's routine is set by its tier in the [roadmap](learning-plan.md).

- **Full routine:** high-value exercises. Every step below.
- **Quick round:** exercises whose concepts the notes already cover. New
  notes are written only for an API that is actually new. The review is
  short, with one explain-it-back question.

## 0. Start of each session (assistant)

1. Read [progress](progress.md) for the next step and any open
   explain-it-back questions.
2. Run `git status` to find exercise files edited since the last commit.
   Because every review ends with a commit, those are edits made after the
   last review. Review them before starting anything new.
3. Ask one or two open questions as a warm-up.

## 1. Before the attempt (assistant)

1. Read the exercise and map each TODO to the concept and API it tests.
2. Check that the notes explain each concept. If a chapter is missing or
   thin, write or extend it using the [chapter layout](#chapter-layout) and
   run its example programs to confirm the documented output.
3. Give you a short brief:
   - which chapter sections to read;
   - each new API: its purpose, what goes in, and what comes out;
   - the TODOs in the file (the whole file is one round);
   - how to run the exercise ([Java tooling](setup/java-tooling.md)).

The brief explains concepts. It doesn't hint at TODO solutions.

## 2. The attempt (you)

The whole file is one round. If you're unsure about an early TODO, ask for
a check-in partway through instead of carrying the doubt through the file.

1. Read the listed sections and run their examples.
2. Before running a TODO, write your prediction as a comment above your
   code, for example `// predict: [3, 5]`. When a stage changes the type,
   note that too, for example `// predict: Map<String, Long>`.
3. Attempt it yourself. Looking up API signatures is fine.
4. When stuck, ask for a hint. Hints climb one rung at a time:
   1. the concept or section to reread;
   2. the operation to use;
   3. the shape of the call;
   4. a full solution, only if you ask for one.
5. Run the file and compare the output with your predictions.
6. Go through the
   [checklist](revision/mistake-patterns.md#checklist-before-asking-for-a-review),
   then ask for a review.

## 3. After the attempt (assistant review)

1. **Run** the current source and record the actual output.
2. **Compare your predictions** with the actual output. A wrong prediction
   counts as a miss even when the code is correct, because it shows where
   the concept still feels unfamiliar.
3. **Check each TODO against its wording:** the required API, the required
   output, and any requested names. A correct result that misses a stated
   requirement counts as a miss.
4. **Test edge cases** that matter for the TODO, such as empty input,
   duplicates, ties, or no match. Use a modified copy outside the
   repository, never your file.
5. **Write the review** as a new dated checkpoint in
   `exercise-reviews/<area>/<exercise>.md`, using the
   [review layout](#review-layout).
6. **Ask two explain-it-back questions** (one in a quick round) in
   interview style, about why the code works and not only what it prints.
   A sentence or two in chat is enough. Unanswered questions are carried
   forward in [progress](progress.md) and come back as a warm-up next
   session.
7. **Update the records:**
   - [progress](progress.md): latest status and the single next step;
   - [mistake patterns](revision/mistake-patterns.md): when a miss repeats an
     earlier one or looks likely to recur;
   - the [roadmap](learning-plan.md) checkbox when the exercise is done;
   - [quick reference](revision/quick-reference.md) and
     [self-check](revision/self-check.md) when a new API was introduced.
8. **Give the commit command** for the exercise and its notes, so the next
   session's `git status` check starts clean.

When you fix something after a review, the next review appends a new
checkpoint. Earlier checkpoints are never rewritten.

## When an exercise counts as done

- Every TODO is verified by running, or marked as checked only by reading,
  with the reason why (for example, random output).
- The explain-it-back questions are answered, or carried forward as open
  questions in progress.
- The roadmap checkbox is ticked.

## How much was checked

Reviews and progress use these words consistently:

| Term | Meaning |
|---|---|
| Verified | The code was run and its output checked |
| By inspection | Checked by reading only; the review says why it wasn't run |
| Reported | You said it; not yet checked |
| Explained | You explained it correctly in your own words |

A correct run shows the code works. Only your explanation shows the concept
is understood.

## Chapter layout

Chapters live in `explanations/`, one per concept, linked from the
[README](README.md).

1. **Purpose:** the problem it solves.
2. **Contract:** inputs, result, and signature: a simplified form first,
   then the real one.
3. **Loop versus stream:** the loop you would write, then the stream
   version of the same task.
4. **Complete example:** imports and expected output included, and run
   before saving.
5. **Execution walkthrough:** which elements are processed in what order,
   when that matters.
6. **Common confusions and pitfalls.**
7. **Interview angle:** how the concept tends to be asked about.
8. **Recap and self-check questions.**
9. **Practice links** to the exercise files.

## Review layout

Reviews live in `exercise-reviews/`, one file per exercise. Each checkpoint
contains:

1. `## Review: YYYY-MM-DD`, plus a short label for a follow-up checkpoint.
2. **What was run:** the method, the inputs, and any scratch variants.
3. **Verdict table:** TODO | Verdict | Evidence.
4. **What was missed:** wrong predictions, requirement misses, bugs, and
   unhandled edge cases.
5. **Style notes:** production idioms, each marked *required* or *optional*.
6. **Explain-it-back:** the questions, a summary of your answers, and their
   status.
7. **Links** to the relevant chapters instead of repeating the lesson.

## Git

The assistant gives the commands and you run them. A commit is suggested
after each finished exercise, containing the exercise and its notes.
Nothing is pushed unless you ask.
