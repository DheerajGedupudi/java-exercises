# Git setup and everyday workflow

[Home](../README.md) · [Java tooling](java-tooling.md)

## Clone, fork, branch

| Operation | Purpose in this project |
|---|---|
| Clone | Put a local Git repository on your computer |
| Fork | Keep your own copy on GitHub, where you can push your attempts |
| Branch | Maintain a separate line of work for practice |

Cloning the original before forking was fine. The existing clone was
connected to the later fork by changing its remote configuration.
One practice branch is sufficient for this learning workflow.

## The configured destinations

The configuration established in this session is:

```text
origin    https://github.com/DheerajGedupudi/java-exercises.git (fetch)
origin    https://github.com/DheerajGedupudi/java-exercises.git (push)
upstream  https://github.com/amigoscode/java-exercises (fetch)
upstream  https://github.com/amigoscode/java-exercises (push)
```

A remote is a saved repository address with a nickname. By the convention
used here, `origin` is your fork and `upstream` is the author's original.

- Fetch downloads commits. It does not by itself merge them into your branch.
- Push uploads commits to a destination where you have permission.
- The two lines per remote describe directions, not transfers already performed.
- `upstream (push)` records an address; it does not grant write access there.
- Nothing synchronizes automatically just because these addresses exist.

Inspect the current configuration with:

```bash
git remote -v
git status --short --branch
git branch -vv
```

The established practice branch is `streams-practice`, tracking
`origin/streams-practice`. Tracking associates the local branch with a
remote branch. It does not make a local commit appear on GitHub automatically.

## Setup history

These commands were already used; they document how the configuration was
created, rather than instructions to repeat now:

```bash
git remote rename origin upstream
git remote add origin https://github.com/DheerajGedupudi/java-exercises.git
git switch -c streams-practice
```

The original README recommends `exercises` and also provides `solutions`.
During initial setup, the stream and functional-interface files on `main`
and `exercises` were checked and were identical in this clone. The practice
branch was created from `main`, with the relevant exercise content present.

## Saving work

Editing a file, staging it, creating a commit, and pushing that commit are
separate steps. Review `git diff` before choosing what to stage. Use your
fork as the destination for your attempts. For an explicit branch push:

```bash
git push origin streams-practice
```

That uploads commits already on the branch; it does not commit uncommitted
files. Fetch author updates from `upstream`, then decide separately how to
integrate them. Read individual solutions only after your own attempt.

## Check your understanding

Explain which copy is local, which GitHub repository you can push to, what
tracking means, and why `git remote -v` does not transfer anything.

References: [GitHub: fork a repository](https://docs.github.com/en/pull-requests/how-tos/work-with-forks/fork-a-repo),
[GitHub: configure fork remotes](https://docs.github.com/en/pull-requests/how-tos/work-with-forks/configuring-a-remote-repository-for-a-fork).

