#!/usr/local/bin/perl

#English Letter Frequency Model
my %engcount = ('a' => 8.167,
				'b' => 	1.492,
				'c' => 	2.782,
				'd' => 	4.253,
				'e' => 	12.702,
				'f' => 	2.228,
				'g' => 	2.015, 
				'h' => 	6.094,	
				'i' => 	6.966,	
				'j' => 	0.153,
				'k' => 	0.772,
				'l' => 	4.025, 
				'm' => 	2.406,	
				'n' => 	6.749,	
				'o' => 	7.507, 
				'p' => 	1.929,	
				'q' => 	0.095, 
				'r' => 	5.987,	
				's' => 	6.327,
				't' => 	9.056,
				'u' => 	2.758,
				'v' => 	0.978,
				'w' => 	2.360,
				'x' => 	0.150,
				'y' => 	1.974,
				'z' => 	0.074,
				);

open(my $cipher,"<data.txt") or die("Could not open file.\n");
foreach $line(<$cipher>) {
	my $lowestcost = 10000;
	my $finalstring = ();
	for($i = 1; $i <= 26; $i++){ # Cycle through variations
		@chararray = split(//,$line);
		@wordcount = ();
		for($j = 0; $j < $#chararray + 1; $j++){
			if ($chararray[$j] eq "z"){
				$chararray[$j] = "a";
			}elsif ($chararray[$j] eq "Z"){
				$chararray[$j] = "A";
			}else{
				($chararray[$j] ne " ") && ($chararray[$j] ne "\n") && do ($chararray[$j] = chr(ord($chararray[$j]) + 1));
			}
			($chararray[$j] ne " ") && ($chararray[$j] ne "\n") && do ($wordcount{$chararray[$j]}++); # Count frequency of letters
		}
		$cost = 0;
		for my $letterkey(keys %wordcount){ # Compare frequency with english frequency model
			$cost += abs((($wordcount{$letterkey}/($#chararray + 1)) * 100) - $engcount{$letterkey});
			delete $wordcount{$letterkey};
		}
		$tempstring = join('',@chararray);
		$line = $tempstring;
		if ($cost < $lowestcost){ # Return string with lowest cost difference
			$finalstring = $tempstring;
			$lowestcost = $cost;
		}
	}
	print $finalstring;
}
close($cipher);
