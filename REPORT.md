# Assignment 4: Dynamic Programming and String Algorithms

## Experiment Design Card

Initial plan recorded before any experiment runs. Results and conclusions will be added after measurement.

| Field | Edit Distance | String search |
|---|---|---|
| Question | How do recursion, a full table, and two rows differ in work, time, and storage as string length grows? | How does KMP avoid repeated comparisons on inputs that make Naive search expensive? |
| Sizes | Both strings have length n. Recursive: 2, 4, 6, 8, 10. Table and two rows: 128, 256, 512, 1,024, 2,048. Small sizes keep exponential recursion practical; larger sizes expose quadratic DP growth. | Text length n = 4,096, 8,192, 16,384, 32,768; pattern length m = n / 64. Growing both lengths exposes the difference between O(n*m) and O(n+m). |
| Inputs | Identical strings generated over a-z show the recursive matching-character shortcut. Disjoint strings (a repeated n times versus b repeated n times) force mismatch branching. DP still fills every interior cell for both types. | Independent random text and pattern over a-z show early mismatches. Adversarial text is a repeated n times; pattern is a repeated m-1 times followed by b. Each Naive alignment almost matches; KMP reuses prefix information. |
| Metrics | Recursive calls, interior DP cells evaluated, character comparisons, and elapsed milliseconds. Calls expose repeated subproblems; cells show DP work. Unused counters are zero and interpreted by algorithm type. | Character comparisons during search and LPS construction, recorded separately; their sum measures total KMP work. Also elapsed milliseconds and match count. |
| Prediction | Disjoint recursive inputs grow exponentially; identical recursive inputs take a linear chain. Both DP versions evaluate n squared cells. Two rows use O(n) extra space for equal-length strings versus O(n squared) for a table. Allocation and cache effects may affect timing. | Random input should need few comparisons per alignment. On adversarial input Naive comparisons grow quadratically because m grows with n; KMP total comparisons grow linearly. Preprocessing may make KMP less attractive on easy inputs. |
| Plots | Recursive calls versus n with logarithmic y-axis; DP cells / n squared versus n; median DP time versus n. Compare both input types. | Total character comparisons versus n on log-log axes, with both algorithms and input types; median search time versus n. |

### Measurement protocol

- Write a custom input generator using `new Random(42)`. Generate each case once and reuse exactly the same strings across applicable algorithms and repetitions. Exclude generation, validation, output, and plotting from timing.
- Use one unrecorded warm-up per case, then five measured runs timed with `System.nanoTime()`. Record fractional milliseconds and plot the median of five samples.
- Write one CSV row per measured run. Planned columns: `algorithm,input,n,m,run,time_ms,recursive_calls,dp_cells,character_comparisons,lps_comparisons,result`. Result means distance for Edit Distance and match count for search.
- Increment counters where work occurs. Count each executed equality comparison between characters, including LPS fallback comparisons. Exclude loop bounds and index comparisons. Count every recursive method entry, including base cases; count evaluated interior DP cells, excluding boundary initialization.
- Timed KMP includes LPS construction. Validate complete search match lists outside timing; match counts alone are insufficient. Cross-check all three distance versions on short inputs and both DP versions on larger ones.
- Reproducibility applies to inputs, answers, and counters. Elapsed times vary across machines and runs. Record the JDK and environment with final results.
- Every plot needs a title, labeled axes with units, a legend where needed, and a one-sentence takeaway. Explain storage from allocated arrays without presenting theoretical sizes as measured counters.

### Changes to the plan

None. If pilot measurements reveal unsuitable sizes or near-zero timings, document revised sizes or timing protocol and the reason here before the final run. Keep at least four sizes per algorithm and a largest-to-smallest ratio of at least four.

## Results and discussion

Pending implementation and measurement. The final report will include plots and takeaways, predictions compared with results, the practical recursive size limit, full-table versus two-row tradeoffs, the string-search choice for the city platform, and a paragraph about timing variability. The completed report will target 2-4 pages.
