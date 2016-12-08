#include <stdio.h>
#include <string.h>
#include <math.h>

char *exns[] = 
{  "parallelogram","centroid","orthocenter","ceva", "menelaus", 
   "affine", "harmonic-set",  "gauss-line", "pappus", "desarge",
   "euler-line","simons", "pascal", "nine-point-circle", "feuerbach",
   "formula1",  "foot-angle", "incenter-harm",  "orth-dual",  "foot-cent",
   "circum-ratio",  "napoleon", "centroid4","trapezoid","butterfly4", 
   "parallelogram1","parallelogram2", "square1","square2","square3",
   "square4",  "cantor", "brahmagupta", "butterfly", "butterfly-ext1", 
   "butterfly-ext2",  "inversion1","inversion2","circle1","circle2",
   "ceva3-1", "ceva3-2", "ceva3-3",      "ceva4","ceva5-1",
   "ceva5-2", "nehring", "trilinear", "harmonic-proj",  "pappus-point",
   "pascal-on-circle", "pascal-general", "kirkman", "steiner", "pascal-conic",
   "echols","equalateral1","equalateral2",   "square-vector1","square-vector2",
   "square-vector3",
 };

main ()
{ int i;
  FILE *in_file, *out_file;
  char txt[200], *cp;

  out_file = fopen("exs","w"); 
  if (out_file == NULL)
    {  printf("Couldn't open exs for saving.\n");
       return;
     }

  for (i=0;i<=63;i++)
    { 
      printf(" %s \n", exns[i]);
      in_file = fopen(exns[i],"r"); 
      if (in_file == NULL)
	{  printf("Couldn't open %s for reading.\n", exns[i]);
	   goto l;
	 }
      fprintf(out_file,"\"%s\",\n\"",exns[i]);
      while(feof(in_file)==0)
	{ fgets(txt,200,in_file);
	  cp = txt;
	  while (*cp!='\n') { fprintf(out_file,"%c",*cp); cp++; }
	  cp = txt;	  
	  cp--;
	  if (*cp=='.')
	    { fprintf(out_file,"\\n\",\n");	  break; }
	  else 	fprintf(out_file,"\\n\\\n");	  
	}
      fclose(in_file);
    }
 l:
  fclose(out_file);
}
