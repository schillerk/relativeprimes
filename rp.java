public class rp
{
	public static void main(String[] args)
	{
		rp test = new rp();
		test.run(50000);
	}

	public void rp()
	{

	}

	public void run(int n)
	{
		int[] primes = getPrimes(n);
		int[][] factors = getFactors(n, primes);
		//printFactors(n, factors);
		double ratio = 0;
		int count = 0;
		for(int i=1; i<n; i++)
		{
			for(int j=1; j<i; j++)
			{
				count++;
				if(i%(n/10) == 0 && j == 1)
				{
					//System.out.println(ratio);
					System.out.println(Math.pow(6/ratio,.5));
				}
				if(isRP(factors, i, j))
				{
					ratio += (1-ratio) / count;
				}
				else
      			{
      				ratio = ratio*(count-1)/(count);
      			}
			}
		}
	}

	public boolean isRP(int[][] factors, int a, int b)
	{
		int i = 1;
		int j = 1;
		while(factors[a][i] != 0 && factors[b][j] != 0)
		{
			if(factors[a][i] == factors[b][j])
			{
				return false;
			}
			if(factors[a][i] < factors[b][j])
			{
				i++;
			}
			else
			{
				j++;
			}
		}
		return true;
	}

	// public void printFactors(int n, int[][] factors)
	// {
	// 	int x = 0;
	// 	for(int i=1; i<n; i++)
	// 	{
	// 		System.out.print(i +": ");
	// 		while(factors[i][x] != 0)
	// 		{
	// 			System.out.print(factors[i][x] + " ");
	// 			x++;
	// 		}
	// 		x = 0;
	// 		System.out.println();
	// 	}
	// }

	public int[][] getFactors(int n, int[] primes)
	{
		int[][] factors = new int[n][20];
		int index = 0;
		int lp = 0;
		for(int i=2; i<n; i++)
		{
			if(i == primes[index])
			{
				factors[i][1] = primes[index];
				factors[i][0] = 1;
				index++;
			}
			else
			{
				while(i%primes[lp] != 0)
				{
					lp++;
				}
				for(int j=1; j<=factors[i/primes[lp]][0]; j++)
				{
					factors[i][j] = factors[i/primes[lp]][j];
					factors[i][0] = factors[i/primes[lp]][0];
				}
				insertFactor(factors, primes[lp], i);
				lp = 0;
			}
		}
		return factors;
	}

	public int[][] insertFactor(int[][] factors, int factor, int row)
	{
		int i = 0;
		boolean done = false;
		while(!done)
		{
			i++;
			if(factors[row][i] == factor)
			{
				done = true;
			}
			else if(factors[row][i] > factor)
			{
				for(int j=factors[row][0]+1; j>i; j--)
				{
					factors[row][j] = factors[row][j-1];
				}
				factors[row][i] = factor;
				factors[row][0]++;
				done = true;
			}
		}
		return factors;
	}
	//standard implementation of sieve of eratosthenes
	public int[] getPrimes(int n)
	{
		int[] temp = new int[100*n];
		int[] primes = new int[n];
		int ln = (int)Math.pow(100*n, .5) + 10;
		for(int i=2; i<temp.length; i++)
		{
			temp[i] = i;
			for(int j=2; j<ln; j++)
			{
				if(i%j == 0 && i != j)
				{
					temp[i] = 0;
					j += ln;
				}
			}
		}
		int i = 0;
		int j = 0;
		while(i < primes.length)
		{
			if(temp[j] != 0)
			{
				primes[i] = temp[j];
				i++;
			}
			j++;
		}
		return primes;
	}
}