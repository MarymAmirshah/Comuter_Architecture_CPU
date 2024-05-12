import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final int NUM_REGISTERS = 16;

    private static final String[] REGISTERS = {
            "$r0", "$r1", "$r2", "$r3", "$r4", "$r5", "$r6", "$r7",
            "$r8", "$r9", "$r10", "$r11", "$r12", "$r13", "$r14", "$r15"
    };

    private static final Map<String, Integer> OPCODES = new HashMap<>();

    static {
        OPCODES.put("ADD", 0b0000);
        OPCODES.put("SUB", 0b0001);
        OPCODES.put("MUL", 0b0010);
        OPCODES.put("OR", 0b0011);
        OPCODES.put("AND", 0b0100);
        OPCODES.put("NOR", 0b0101);
        OPCODES.put("NAND", 0b0110);
        OPCODES.put("BEQ", 0b0111);
        OPCODES.put("LW", 0b1000);
        OPCODES.put("SW", 0b1001);
        OPCODES.put("ADDI", 0b1010);
        OPCODES.put("ORI", 0b1011);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the instruction: ");
        String instructionInput = scanner.nextLine();
        scanner.close();
        String instruction = instructionInput.replaceAll(",", "");
        assemble(instruction);

    }

    private static void assemble(String instruction) {
        String[] parts = instruction.split(" ");

        String opcode = parts[0];
        String rd = parts[1];
        String rs = parts[2];
        String rt = parts[3];

        int opcodeValue = getOpcodeValue(opcode);
        int rdValue = getRegisterValue(rd);
        int rsValue = getRegisterValue(rs);
        int rtValue = getRegisterValue(rt);



        System.out.print("Machine Code: ");
        System.out.print(LeftZero(Integer.toBinaryString(opcodeValue),"0000")+ " ");
        System.out.print(LeftZero(Integer.toBinaryString(rdValue),"0000")+ " ");
        System.out.print(LeftZero(Integer.toBinaryString(rsValue),"0000")+ " ");
        System.out.print(LeftZero(Integer.toBinaryString(rtValue),"0000")+ " ");

//        int machineCode = (opcodeValue << 12) | (rdValue << 8) | (rsValue << 4) | rtValue;
//        return Integer.toBinaryString(machineCode);


    }

    private static int getOpcodeValue(String opcode) {
        Integer value = OPCODES.get(opcode);
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException("Invalid opcode: " + opcode);
    }

    private static int getRegisterValue(String register) {
        for (int i = 0; i < REGISTERS.length; i++) {
            if (REGISTERS[i].equals(register)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid register: " + register);
    }

    public static String addSpacing(String input) {
        StringBuilder result = new StringBuilder();
        int groupSize = 4; // تعداد ارقام در هر گروه

        for (int i = 0; i < input.length(); i += groupSize) {
            String group = input.substring(i, Math.min(i + groupSize, input.length()));
            result.append(group).append(" ");
        }

        return result.toString().trim();
    }

    public static String LeftZero(String binaryNumber1, String binaryNumber2){
//        String binaryNumber1 = "0001"; // عدد باینری اول
//        String binaryNumber2 = "0010"; // عدد باینری دوم

        BigInteger number1 = new BigInteger(binaryNumber1, 2);
        BigInteger number2 = new BigInteger(binaryNumber2, 2);

        BigInteger sum = number1.add(number2);

        String binarySum = sum.toString(2);
        String paddedBinarySum = String.format("%0" + Math.max(binaryNumber1.length(), binaryNumber2.length()) + "d", Long.parseLong(binarySum));
        return paddedBinarySum;
    }
}