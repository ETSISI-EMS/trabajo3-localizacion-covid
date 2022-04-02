
<h1>Service by SoftwareImprovementGroup.com to evaluate Code Quality inside your CI/CD</h1>

When: April 2020

How: Online with [BetterCodeHub.com](https://bettercodehub.com)


## Why evaluate your code? 

Spend less time fixing bugs. And more time shipping new features.

It is amazing to see how far our industry has come over the past decades. New technologies, frameworks, agile processes - some of which were only recently invented - now define the way we work today. One prominently missing building block in the modern development setup is a standardised way to normatively measure code quality - a SMART 'Definition of Done' shared by all developers.

Based on 15+ years of research and tens of thousands of measurements on IT systems from numerous industries, SIG presents 'Better Code Hub’ (BCH), an online environment that allows anyone to measure how their code compares to 10 rules for future-proof code. These ten rules code are described in SIG's book 'Building Maintainable Software', which was published with O'Reilly Media.

A clean codebase keeps development velocity high and makes it easy for developers to start contributing. Better Code Hub indicates just the right amount of refactoring needed to keep it compliant.
Better Code Hub checks your code for compliance against 10 benchmarked software engineering guidelines. It gives immediate feedback on where to focus for quality improvements. 17 modern programming languages are supported.


## Evaluating your code by using Better Code Hub (BCH).

1. Your code will be automatically evaluated and benchmarked against 10 guidelines for building future-proof code. Find a concise reference card of the guidelines [here](https://cdn-images-1.medium.com/max/1200/1*TS-ZTeI7sQS7dy_AlMqSXQ.png)

2. You can check your code against the guidelines using [Better Code Hub](https://bettercodehub.com), a GitHub integration provided by [Software Improvement Group SIG](https://www.sig.eu/better-code-hub/). Better Code Hub integrates with the GitHub Flow and can easily run in your CI/CD pipeline on all your Pull Requests and commits.

3. 17 modern programming languages are [supported](https://bettercodehub.com/docs/configuration-manual) with a maximum of 200 KLoc per code base.

4. Each repository gets a score in Better Code Hub. The more guidelines you get right, the higher the score. The maximum is 10 out of 10. All guidelines are equally important so please aim for a 10 for new codebases.

5. Find your scores inside the GitHub conversation flow at the commits and pull request per branch.

6. Press play in the BetterCodeHub interface and run your analysis on master.

7. Use the score to discuss refactoring priorities with your team. The scores can be compared over teams to have a common understanding of code quality. When a guideline is compliant ( green ) it is time to move along and shift your attention to other parts of the code base. 'Clean is clean enough'


## How to get started.

1. Make sure that you are a member of [ETSISI-EMS](https://github.com/ETSISI-EMS). Membership of the ETSISI-EMS organization allows you to store and analyse private repo's on GitHub.

2. Visit [bettercodehub.com](https://bettercodehub.com), login with your GitHub handle and accept the 3 scopes, please!

3. Press Play button to get the first feedback on the 10 guidelines, browse the report, pick refactor candidates and turn them into GitHub Issues.

4. Enable Push & Pull Request support by clicking on the PR icon on the card, this will make BetterCodeHub analyse your commits and pull requests.

5. Send a PR to add your badge code to be added to the scoreboard.md


## Some tips on using BCH.

1. **Snoozing** If there is a refactoring candidate in the list that you decide not to solve (for now), can snooze it. This hides it from view but does not affect your score. You can then focus on other refactoring candidates to bring your code base at an acceptable quality level. You can unsnooze a candidate when your insights change.

2. **Component depth** Under the “Keep Architecture Components Balanced” guideline, you can check whether BCH has correctly detected the top-level components of your system. If not, you can interactively try different zoom levels and then configure the correct zoom level in a configuration file (bettercodehub.yml). BCH uses the folders structure in your system to zoom.

3. **Exclusions** If there are files, languages or directories in your code base that are not within your development/maintenance scope, but not automatically excluded from analysis by BCH, you can configure additional exclusions in the configuration file. But first consider to take them out of your repository altogether by using dependency management.



## How to get support.

1. Send an email to bettercodehub@sig.eu


## Further reading on Future-Proof code.

Writing quality code [under time pressure](https://hackernoon.com/writing-quality-code-under-time-pressure-62ebeb5f39c5)

How a Hackathon [appreciates quality code](https://dev.to/jstvssr/how-a-hackathon-appreciates-quality-code)

Why Blockhain [needs high quality code ](https://medium.com/@jstvssr/why-blockchain-needs-future-proof-code-cb09b39175e1#.bqfmcig55)

And this post on how [Dutch Blockchain Hackathon](https://dev.to/jstvssr/how-a-hackathon-appreciates-quality-code) appreciated quality code.

What are common [pitfalls of metrics?](https://queue.acm.org/detail.cfm?id=2229115)  

A different code [quality philiosophy](https://medium.com/softwareimprovementgroup/constraints-that-drive-creativity-6645377ca3c)


Let's build some great software !!

[Michiel](https://github.com/michielcuijpers)

