This is a **very deep “history + systems” question**, and the book is intentionally subtle here. Let’s answer it cleanly in three layers:

1. **Why SQL was not “just used” originally**
2. **What MapReduce actually solved that SQL engines at the time could not**
3. **What the author is ultimately concluding about MapReduce**

I’ll be precise and a bit blunt where needed.

---

## 1. Why couldn’t we “just use SQL”?

### Short answer

> Because **distributed SQL engines did not exist (or were not practical) at Google-scale when MapReduce was created**, and the existing relational databases were the *wrong abstraction* for Google’s core problems at the time.

Now the longer, honest answer.

---

### 1.1 SQL assumes a powerful centralized database engine

Classic SQL systems assumed:

* a **single logical database**
* strong consistency
* centralized metadata
* expensive hardware
* vertical scaling (bigger machine)

Google’s reality in the early 2000s:

* **thousands of cheap machines**
* frequent failures
* petabytes of data
* batch analytics, not OLTP
* data spread across a distributed file system (GFS)

SQL engines of that era:

* did not scale to thousands of nodes
* did not tolerate frequent failures well
* did not assume data lives as files across machines
* were extremely hard to operate at Google scale

So SQL wasn’t rejected philosophically — it was **operationally infeasible**.

---

### 1.2 Google’s data wasn’t relational in the first place

Google’s core workloads:

* web crawl data
* logs
* indexes
* documents
* graph-like link data
* massive event streams

These were:

* semi-structured
* schema-light
* append-heavy
* processed in bulk

For many jobs, SQL tables were an **awkward fit** compared to:

* “read a file”
* “process records”
* “emit derived data”

MapReduce fit naturally on top of:

* GFS (Google File System)
* immutable files
* batch processing

---

### 1.3 SQL engines hid too much control

This may sound ironic given later arguments, but at the time:

* Google needed **predictable control over execution**
* They wanted to decide:

  * data partitioning
  * memory usage
  * intermediate materialization
* Query optimizers of the time were:

  * complex
  * opaque
  * not designed for thousands of machines

MapReduce gave Google:

> “A simple, explicit, controllable execution model that we can scale and reason about.”

This was a *feature* then.

---

## 2. What MapReduce actually solved (that SQL didn’t)

MapReduce wasn’t about query expressiveness.
It was about **distributed execution reliability**.

### MapReduce’s real contributions:

1. **Automatic parallelization**
2. **Automatic fault tolerance**
3. **Data-local execution**
4. **Simple programming model**

The key innovation:

> You write *pure functions*, the system handles distribution, retries, and failures.

That was revolutionary.

---

### Why SQL engines couldn’t do this yet

Because they lacked:

* distributed schedulers
* fault-tolerant execution DAGs
* data-local task placement
* commodity-cluster assumptions

Modern distributed SQL engines **learned these lessons later**.

---

## 3. So what is the author *concluding* about MapReduce?

This is the most important part.

### The author is **not praising MapReduce as a final solution**

The tone of the chapter is very clear (if you read carefully):

> **MapReduce was an important step — but it is low-level, awkward for many queries, and often inferior to declarative query languages.**

Let’s decode the signals.

---

### 3.1 MapReduce is described as “somewhere in between”

The author explicitly says:

* not declarative
* not fully imperative
* awkward middle ground

That’s not praise — that’s *classification with limits*.

---

### 3.2 MapReduce is called “fairly low-level”

That’s a strong phrase in database literature.

Low-level means:

* verbose
* harder to optimize
* easier to misuse
* more work for the programmer

This is reinforced by:

> “you have to write two carefully coordinated JavaScript functions”

That’s a usability critique.

---

### 3.3 Declarative languages are framed as *better long-term*

The author repeatedly emphasizes:

* optimizers
* parallel execution
* abstraction
* evolution

And then says:

> “a declarative query language offers more opportunities for a query optimizer”

This is a **direct argument against MapReduce as a primary query interface**.

---

### 3.4 The “reinventing SQL” sentence is the key conclusion

This line is the author’s verdict:

> “The moral of the story is that a NoSQL system may find itself accidentally reinventing SQL, albeit in disguise.”

This is not accidental wording.

The author is saying:

* once systems mature
* once users want expressive queries
* once optimization matters
* once parallelism is needed

They drift **back toward declarative, SQL-like abstractions**.

MongoDB adding the aggregation pipeline is presented as *evidence*.

---

## 4. Final distilled conclusion (this is the answer you want to internalize)

### Why we didn’t just use SQL

* SQL engines at the time could not run reliably at Google-scale
* The workloads were file-based, batch-oriented, and schema-light
* MapReduce solved *distribution and fault tolerance*, not querying elegance

### What MapReduce ultimately is

* A **distributed execution framework**
* Not a great **query language**
* A stepping stone, not a destination

### What the author concludes

> **MapReduce was historically necessary, but declarative query languages are the better long-term interface for data processing — even at scale.**

Or even shorter:

> MapReduce taught systems how to scale; SQL taught systems how to think.

---

If you want next, I can:

* compare **MapReduce vs distributed SQL execution plans**
* explain how **Spark/DAG engines generalize MapReduce**
* show how **query optimizers “compile” SQL into MapReduce-like stages**
* connect this directly to **modern cloud data warehouses**

Just say the word.
