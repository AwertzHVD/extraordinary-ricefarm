public class ExslTabelleAuslesen
{



public static void main(final String[] args)
{
int[] ar = new int[0];
try
{
final File file = new File("C:\\Workspaces\\workspace_OpenJDK-11\\ExslTabelleAuslesen\\2022.xlsx");
// creating a new file instance
final FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
// creating Workbook instance that refers to .xlsx file
final XSSFWorkbook wb = new XSSFWorkbook(fis);
final XSSFSheet sheet = wb.getSheetAt(1); // creating a Sheet object to retrieve object
final Iterator<Row> itr = sheet.rowIterator(); // iterating over excel file
while (itr.hasNext())
{
final Row row = itr.next();
final Cell cell = CellUtil.getCell(row, 1);
try
{
System.out.println(cell.getStringCellValue());
}
catch (final Exception e)
{
final int[] newAr = new int[ar.length + 1];



for (int i = 0; i < ar.length; i++)
{
newAr[i] = ar[i];



}
newAr[newAr.length - 1] = (int) cell.getNumericCellValue();
System.out.println((int) cell.getNumericCellValue());
ar = newAr;
}
}
}
catch (final Exception e)
{
e.printStackTrace();
}
System.out.println("======================");
for (int i = 0; i < ar.length; i++)
{
System.out.println(ar[i]);
}
}



}