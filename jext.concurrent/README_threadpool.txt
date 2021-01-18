
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory) {
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              RejectedExecutionHandler handler) {
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
    public void execute(Runnable command) {

    public void shutdown() {

    public List<Runnable> shutdownNow() {

    public boolean isShutdown() {

    public boolean isTerminating() {

    public boolean isTerminated() {

    public boolean awaitTermination(long timeout, TimeUnit unit)

    protected void finalize() {

    public void setThreadFactory(ThreadFactory threadFactory) {

    public ThreadFactory getThreadFactory() {

    public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {

    public RejectedExecutionHandler getRejectedExecutionHandler() {

    public void setCorePoolSize(int corePoolSize) {

    public int getCorePoolSize() {

    public boolean prestartCoreThread() {

    void ensurePrestart() {

    public int prestartAllCoreThreads() {

    public boolean allowsCoreThreadTimeOut() {

    public void allowCoreThreadTimeOut(boolean value) {

    public void setMaximumPoolSize(int maximumPoolSize) {

    public int getMaximumPoolSize() {

    public void setKeepAliveTime(long time, TimeUnit unit) {

    public long getKeepAliveTime(TimeUnit unit) {

    public BlockingQueue<Runnable> getQueue() {

    public boolean remove(Runnable task) {

    public void purge() {


    /* Statistics */

    public int getPoolSize() {

    public int getActiveCount() {

    public int getLargestPoolSize() {

    public long getTaskCount() {

    public long getCompletedTaskCount() {


    /* Extension hooks */

    protected void beforeExecute(Thread t, Runnable r) { }

    protected void afterExecute(Runnable r, Throwable t) { }

    protected void terminated() { }

