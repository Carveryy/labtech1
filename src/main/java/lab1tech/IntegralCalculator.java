package lab1tech;

public class IntegralCalculator {
    public double calculate(double a, double b, int n, Function f, int threadsCount) throws InterruptedException {
        double h = (b - a) / n;
        double[] partialSums = new double[threadsCount];
        Thread[] threads = new Thread[threadsCount];

        int intervalsPerThread = n / threadsCount;
        int remainder = n % threadsCount;

        int currentStart = 1;
        for (int i = 0; i < threadsCount; i++) {
            int intervals = intervalsPerThread + (i < remainder ? 1 : 0);
            int start = currentStart;
            int end = currentStart + intervals - 1;
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                double sum = 0;
                for (int k = start; k <= end; k++) {
                    double x_k = a + k * h;
                    double x_k_minus_1 = a + (k - 1) * h;
                    double mid = (x_k_minus_1 + x_k) / 2.0;

                    sum += 2 * f.calculate(mid);
                    if (k < n) {
                        sum += f.calculate(x_k);
                    }
                }
                partialSums[threadIndex] = sum;
            });
            threads[i].start();
            currentStart = end + 1;
        }

        for (Thread t : threads) {
            t.join(); // Очікування завершення дочірніх потоків
        }

        double totalSum = 0.5 * f.calculate(a) + 0.5 * f.calculate(b);
        for (double s : partialSums) {
            totalSum += s;
        }

        return (h / 3.0) * totalSum;
    }
}