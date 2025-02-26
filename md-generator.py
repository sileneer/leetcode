import os


def generate_markdown_index(directory="solutions", output_file="README.md"):
    """Generates a markdown content page linking to all markdown files in the solutions directory."""

    if not os.path.exists(directory):
        print(f"Directory '{directory}' does not exist.")
        return

    md_files = sorted(f for f in os.listdir(directory) if f.endswith(".md"))

    if not md_files:
        print("No markdown files found.")
        return

    content = ["# leetcode", "", "My solution repo for LeetCode. The solutions are in Java.", "", "## List of Solutions", "", "| LeetCode | Solution |", "| --- | --- |"]

    for md_file in md_files:
        title = md_file.replace("-", " ").replace(".md", "").capitalize()
        md_link = f"[{md_file}](solutions/{md_file})"
        leetcode_link = f"[{int(title[:5])}. {title[5:].capitalize()}](https://leetcode.com/problems/{title[5:].replace(' ', '-').lower()})"
        content.append(f"| {leetcode_link} | {md_link} |\\")

    with open(output_file, "w", encoding="utf-8") as f:
        f.write("\n".join(content))

    print(f"Generated {output_file} with {len(md_files)} entries.")


if __name__ == "__main__":
    generate_markdown_index()
