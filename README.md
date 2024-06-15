The 7 frames with a random list of numbers worked the best, likely because with more frames,
fewer processes need to be removed from memory and then added back in again.
Depending on the numbers, sometimes FIFO performed better and sometimes LRU performed better,
but I suspect with real numbers LRU would perform better, due to recently used pages
being more likely to be needed again soon with real paging, rather than random numbers.
Opt always performed as well or better than the other two since it was able to look to the
future to determine which pages should be kept in memory.