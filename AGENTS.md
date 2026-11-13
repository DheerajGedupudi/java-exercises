# Repository instructions

This repo is a Java streams learning workspace. The learner writes all
exercise code. The assistant prepares notes, reviews attempts, and keeps
the learning records. These instructions apply to any assistant.

## Working with the learner

- Ask clarifying questions before proceeding, one at a time.
- Do not code until the learner confirms the plan.
- For complex tasks, write the plan to `plan.md` first. It is temporary:
  delete it when the task is done, and never commit it.
- Never edit exercise source files. Test variants go in a temporary
  copy outside the repository.
- Give git commands for the learner to run instead of committing or
  pushing yourself.

## Start here

- `learning-notes/protocol.md`: the routine for every exercise and session.
- `learning-notes/learning-plan.md`: the goal and the roadmap.
- `learning-notes/progress.md`: latest status, next step, open questions.

## Keeping the notes

- Save substantive explanations in `learning-notes/` during the same task,
  covering Java concepts, Git workflow, and explanations from reviews.
- Follow the structure in `learning-notes/README.md`: one chapter per
  concept in `explanations/`, and one review file per exercise in
  `exercise-reviews/`. Add new dated checkpoints instead of rewriting old
  ones, and link to the chapter rather than repeating it. Short revision
  material goes in `revision/`.
- Use the chapter and review layouts and the evidence terms from the
  protocol. Complete example programs include imports and expected output.
- Link new notes from `learning-notes/README.md`, along with related
  topics and exercise files. Keep the examples and step-by-step
  explanations needed for revision. Add short recaps and questions; don't
  copy the conversation.
